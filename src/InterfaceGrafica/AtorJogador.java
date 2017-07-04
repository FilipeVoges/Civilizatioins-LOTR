package InterfaceGrafica;

import Entidades.Cidade.Cidade;
import Entidades.Construcao.Arquearia;
import Entidades.Construcao.Construcao;
import Entidades.Construcao.Estabulo;
import Entidades.Construcao.Principal;
import Entidades.Construcao.Quartel;
import Entidades.Jogada.Jogada;
import Entidades.Jogador.Jogador;
import Entidades.Mapa.Mapa;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Tropa;
import Enumeradores.Raca;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class AtorJogador extends JFrame {
	
    protected JTable tabela;
    protected JScrollPane mapaContainer;
    protected JPanel mapaPanel, infoPanel, botaoPanel, labelPanel;
    protected JButton btnIniciaJogo, btnConectar, btnPassaVez, btnDesistir;
    protected JLabel infoNomeJogador, infoRacaJogador, labelEspaco, infoRecursos;
    protected Mapa mapa;
    protected String server;
    protected Jogador jogadorMapa;
    protected Cidade cidadeMapa;
      	
    public AtorJogador(Mapa mapa){  
        
        this.mapa = mapa;
        
        this.setLayout(new BorderLayout());
    
        //inicia mapa
        tabela = new JTable(mapa.getTamX(),mapa.getTamX());
	tabela.setBackground(Color.GREEN.darker().darker());
	tabela.setForeground(Color.WHITE);
        tabela.setTableHeader(null);	//Esconde o cabeçalho da tabela
        tabela.setEnabled(false);	// Impede de selecionar e editar campos
        mapaContainer = new JScrollPane(tabela);
        mapaPanel = new JPanel();
        mapaPanel.add(mapaContainer, BorderLayout.CENTER);
        this.add(mapaPanel, BorderLayout.NORTH);
        
        //inicia painel de informações
        
        infoNomeJogador = new JLabel();
        infoRacaJogador = new JLabel();
        infoRecursos = new JLabel();
        labelEspaco = new JLabel("  ");
        btnIniciaJogo = new JButton("Iniciar Jogo");
        btnConectar = new JButton("Conectar a partida");
        btnPassaVez = new JButton("Passa a Vez");
        btnDesistir = new JButton("Desistir");
        infoPanel = new JPanel();
        botaoPanel = new JPanel();
        labelPanel = new JPanel();
        botaoPanel.setLayout(new FlowLayout());
        labelPanel.setLayout(new FlowLayout());
        infoPanel.setLayout(new BorderLayout());
        botaoPanel.add(btnConectar);
        botaoPanel.add(btnIniciaJogo);
        botaoPanel.add(btnPassaVez);
        botaoPanel.add(btnDesistir);
        labelPanel.add(infoNomeJogador);
        labelPanel.add(labelEspaco);
        labelPanel.add(infoRacaJogador);
        labelPanel.add(labelEspaco);
        labelPanel.add(infoRecursos);
        infoPanel.add(botaoPanel,BorderLayout.SOUTH);
        infoPanel.add(labelPanel,BorderLayout.NORTH);
        btnIniciaJogo.setVisible(false);
        btnPassaVez.setVisible(false);
        btnDesistir.setVisible(false);
        this.add(infoPanel, BorderLayout.SOUTH);
        
        ouvidorClique();
        
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocation(450, 100);
    }
    
    /***************************************************************************/
    private void ouvidorClique(){
        //ouvidor de clique mapa
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.rowAtPoint(e.getPoint());
                int coluna = tabela.columnAtPoint(e.getPoint());
                Posicao clique = new Posicao(linha, coluna);
                Object o = pegaValorPosicao(clique);
                
                Jogada jogada = mapa.realizaJogada(clique, jogadorMapa, o);
                
                atualizaTela(jogada);
           }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean conectado = conectar();
                if(conectado){
                    btnIniciaJogo.setVisible(true);
                    btnConectar.setVisible(false);
                    mapa.exibirMensagem("Conexão Estabelecida");
                }else{
                    mapa.exibirMensagem("Erro ao se conectar com o servidor");
                }
            }
        });
        
        btnIniciaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapa.iniciarPartida();
                btnIniciaJogo.setVisible(false);
                btnPassaVez.setVisible(true);
                btnDesistir.setVisible(true);
                cidadeMapa = new Cidade(jogadorMapa);
                posicionaJogadores(1);
                jogadorMapa.recebeVez();
                infoRecursos.setText("Recursos: " + cidadeMapa.getRecursos());
            }
        });
        
        btnPassaVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogadorMapa.passaVez();
                btnPassaVez.setVisible(false);
            }
        });
        
        btnDesistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int desistiu = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja desistir da partida?");
                if(desistiu == 0){
                    mapa.informarDesistencia(jogadorMapa);
                    limparMapa();
                    btnIniciaJogo.setVisible(true);
                    btnPassaVez.setVisible(false);
                    btnDesistir.setVisible(false);
                    infoRecursos.setText("");
                }
            }
        });
    }
    
    /***************************************************************************/
    private boolean conectar(){
        String nome = "";
        String racaString = "";
        Raca raca = null;
        nome = JOptionPane.showInputDialog("Insira seu nome");
        String[] options = new String[] {"Elfo", "Humano", "Uruk Hai", "Orc"};             
        int response = JOptionPane.showOptionDialog(
            null, 
            "Selecione a raça que você deseja", 
            "Seleção de Raça",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE,
            null, 
            options, 
            options[0]
        );
        
        switch(response){
            case 0:
                raca = Raca.ELFO;
                racaString = "Elfo";
            break;
            case 1:
                raca = Raca.HUMANO;
                racaString = "Humano";
            break;
            case 2:
                raca = Raca.URUK_HAI;
                racaString = "Uruk Hai";
            break;
            case 3:
                raca = Raca.ORC;
                racaString = "Orc";
            break;
        }
        
        server = JOptionPane.showInputDialog("Insira o endereço do servidor no qual deseja se conectar");
        //TODO: Fazer a parte de conexão com o NetGames
        
        jogadorMapa = new Jogador(nome, raca, 0);
        infoNomeJogador.setText("Jogador: " + nome);
        infoRacaJogador.setText("Raça: " + racaString);
        limparMapa();
        return true;
    }
    
    
    public void atualizaTela(Jogada jogada){
        if(jogada != null){
            switch(jogada.getTipoJogada()){

                case ATACAR:
                    System.out.println("Atacar");
                    Tropa atacante = (Tropa) jogada.getAntigo();
                    
                    setaValorPosicao(atacante.getPosicao(), atacante);
                    if(jogada.getModificado().getClass().getSuperclass() == Construcao.class){
                        Construcao alvo = (Construcao) jogada.getModificado();
                        setaValorPosicao(alvo.getPosicao(), alvo);

                    }else if(jogada.getModificado().getClass().getSuperclass() == Tropa.class){ 
                        System.out.println("Tropa atacada");
                        Tropa alvo = (Tropa) jogada.getModificado();
                        setaValorPosicao(alvo.getPosicao(), alvo);  
                    }
                break;

                case REFORMA_CONSTRUCAO:
                    System.out.println("Reformar construção");
                    Construcao reformada = (Construcao) jogada.getModificado();
                    setaValorPosicao(reformada.getPosicao(), reformada);
                break;

                case NOVA_TROPA:
                    System.out.println("Nova tropa");
                    Tropa novaTropa = (Tropa) jogada.getModificado();
                    Construcao cons = (Construcao) jogada.getAntigo();
                    Posicao posTropa = posicaoLivreMaisProxima(cons.getPosicao());
                    novaTropa.setPosicaoAtual(posTropa);
                    setaValorPosicao(posTropa, novaTropa);      
                break;

                case MOVIMENTAR:
                    System.out.println("Movimentar");
                    Posicao antiga = (Posicao) jogada.getAntigo();
                    Tropa atual = (Tropa) jogada.getModificado();
                    setaValorPosicao(atual.getPosicao(), atual);
                    setaValorPosicao(antiga, null);
                break;

                default:
                break;
            }
        }
        infoRecursos.setText("Recursos: " + cidadeMapa.getRecursos()); 
    }

    //Limpar Mapa
    public void limparMapa(){
        for(int i = 0; i< mapa.getTamX(); i++){
            for(int j = 0; j< mapa.getTamY(); j++){
                tabela.setValueAt(null, i, j);
            }
        }
    }
    
    //Setter
    public void setaValorPosicao(Posicao p, Object item){
        tabela.setValueAt(item, p.getX(), p.getY());
    }
    public void setaValorPosicao(int x, int y, Object item){
        tabela.setValueAt(item, x, y);
    }
    
    //Getter
    public Object pegaValorPosicao(Posicao p){
       return tabela.getValueAt(p.getX(), p.getY());
    }
    
    public Object pegaValorPosicao(int x, int y){
       return tabela.getValueAt(x, y);
    }
    
    /***************************************************************************/
    public Posicao posicaoLivreMaisProxima(Posicao atual){
        Posicao retorno = null;
        if(this.pegaValorPosicao(atual.getX() + 1, atual.getY() + 1) == null){
            retorno = new Posicao(atual.getX() + 1, atual.getY() + 1);
        }else if(this.pegaValorPosicao(atual.getX() + 1, atual.getY()) == null){
            retorno = new Posicao(atual.getX() + 1, atual.getY());
        }else if(this.pegaValorPosicao(atual.getX() + 1, atual.getY() - 1) == null){
            retorno = new Posicao(atual.getX() + 1, atual.getY() - 1);
        }else if(this.pegaValorPosicao(atual.getX(), atual.getY() - 1) == null){
            retorno = new Posicao(atual.getX(), atual.getY() - 1);
        }else if(this.pegaValorPosicao(atual.getX() - 1, atual.getY() - 1) == null){
            retorno = new Posicao(atual.getX() - 1, atual.getY() - 1);
        }else if(this.pegaValorPosicao(atual.getX() - 1, atual.getY()) == null){
            retorno = new Posicao(atual.getX() - 1, atual.getY());
        }else if(this.pegaValorPosicao(atual.getX() - 1, atual.getY()+ 1) == null){
            retorno = new Posicao(atual.getX() - 1, atual.getY()+ 1);
        }else if(this.pegaValorPosicao(atual.getX(), atual.getY() + 1) == null){
            retorno = new Posicao(atual.getX(), atual.getY() + 1);
        }
        return retorno;
    }  
    
    /***************************************************************************/
    public void posicionaJogadores(int qtdeJogadores){
        for(int i = 0; i< qtdeJogadores; i++){
            System.out.println("Primeiro carinha");
            //posicina edificio principal
            Posicao temp = mapa.getPosInicialMapa().get(i);
            System.out.println(temp.getX() + " " + temp.getY());
            setaValorPosicao(
                    temp, 
                    cidadeMapa.construir(Principal.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
            );
            
            
            
            //posiciona Arquearia
            if(temp.getX() > 2) temp.setX(temp.getX() - 2);
            else temp.setX(temp.getX() + 2);
            
            setaValorPosicao(
                    temp, 
                    cidadeMapa.construir(Arquearia.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
            );
            
            //posiciona Estabulo
            if(temp.getY() > 2) temp.setY(temp.getY() - 2);
            else temp.setY(temp.getY() + 2);     
            
            setaValorPosicao(
                    temp, 
                    cidadeMapa.construir(Estabulo.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
            );
            
            //posiciona Quartel
            if(temp.getX() > 4 )temp.setX(temp.getX() + 2);
            else temp.setX(temp.getX() - 2); 
            
            setaValorPosicao(
                    temp, 
                    cidadeMapa.construir(Quartel.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
            );
        }
    }
    
}
      