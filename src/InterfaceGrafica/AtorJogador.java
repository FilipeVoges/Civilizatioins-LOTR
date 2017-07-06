package InterfaceGrafica;

import Entidades.Cidade.Cidade;
import Entidades.Construcao.Arquearia;
import Entidades.Construcao.Construcao;
import Entidades.Construcao.Estabulo;
import Entidades.Construcao.Principal;
import Entidades.Construcao.Quartel;
import Entidades.Gollum.Gollum;
import Entidades.Jogada.JogadaMapa;
import Entidades.Jogador.Jogador;
import Entidades.Mapa.Mapa;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Heroi;
import Entidades.Tropa.Mago;
import Entidades.Tropa.Tropa;
import Enumeradores.TipoJogada;
import Rede.AtorNetGames;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
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

public class AtorJogador extends JFrame{
	
    protected JTable tabela;
    protected JScrollPane mapaContainer;
    protected JPanel mapaPanel, infoPanel, botaoPanel, labelPanel;
    protected JButton btnIniciaJogo, btnConectarServidor, btnPassaVez, btnDesistir, btnDesconectar;
    protected JLabel infoNomeJogador, infoRacaJogador, labelEspaco, infoRecursos;
    protected Mapa mapa;
    protected String servidor;
    protected AtorNetGames atorNetGames;
      	
    //construtor de atorJogador
    public AtorJogador(Mapa mapa){  
        
        this.mapa = mapa;
        this.setTitle("Civilization - LOTR");
        this.setLayout(new BorderLayout());
        atorNetGames = new AtorNetGames(this);
    
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
        btnIniciaJogo = new JButton("Iniciar novo Jogo");
        btnConectarServidor = new JButton("Conectar ao Servidor");
        btnPassaVez = new JButton("Passa a Vez");
        btnDesistir = new JButton("Desistir");
        btnDesconectar = new JButton("Desconectar");
        infoPanel = new JPanel();
        botaoPanel = new JPanel();
        labelPanel = new JPanel();
        botaoPanel.setLayout(new FlowLayout());
        labelPanel.setLayout(new FlowLayout());
        infoPanel.setLayout(new BorderLayout());
        botaoPanel.add(btnConectarServidor);
        botaoPanel.add(btnIniciaJogo);        
        botaoPanel.add(btnDesconectar);
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
        btnDesconectar.setVisible(false);
        this.add(infoPanel, BorderLayout.SOUTH);
        
        ouvidorClique();
        
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocation(450, 100);
        this.setVisible(true);
    }
   
    //conjunto dos listeners dos botoes e tabela
    private void ouvidorClique(){
        //ouvidor de clique mapa
        tabela.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int linha = tabela.rowAtPoint(e.getPoint());
                int coluna = tabela.columnAtPoint(e.getPoint());
                
                //se a linha e a coluna forem validas
                if(linha >= 0 && coluna >= 0){
                    
                    Posicao clique = new Posicao(linha, coluna);
                    Object objetoClicado = pegaValorPosicao(clique);
                    
                    //realiza a jogada
                    JogadaMapa jogada = mapa.realizaJogada(clique, mapa.getJogadorMapa(), objetoClicado);
                    
                    //envia a jogada e atualiza a tela
                    if(jogada != null){
                        atorNetGames.enviaJogada(jogada);
                        recebeJogada(jogada);
                    }
                    
                }else {
                    mapa.exibirMensagem("Clique inválido");
                    mapa.getJogadorMapa().setTipoClique(TipoJogada.SELECAO);
                }
               
           }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
        
        btnConectarServidor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean conectado = conectar();
                if(conectado){
                    btnIniciaJogo.setVisible(true);
                    btnConectarServidor.setVisible(false);
                    mapa.exibirMensagem("Conexão Estabelecida");
                }else{
                    mapa.exibirMensagem("Erro ao se conectar com o servidor");
                }
            }
        });
        
        btnIniciaJogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {    
                    //int qtdeJogadores = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de jogadores na partida"));
                    atorNetGames.iniciarPartidaRede(2);
                    mapa.getJogadorMapa().recebeVez();
                    btnPassaVez.setVisible(true);
                    iniciaJogo();
                    
                }catch(NaoConectadoException exception){
                    mapa.exibirMensagem("Você não esta conectado a nenhum servidor");
                    exception.printStackTrace();
                }catch(Exception exception){
                    mapa.exibirMensagem("Limite de jogadores excedido");
                }
            }
        });
        
        btnPassaVez.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapa.getJogadorMapa().passaVez();
                btnPassaVez.setVisible(false);
                JogadaMapa jogada = new JogadaMapa(mapa.getJogadorMapa(), null, TipoJogada.PASSA_VEZ);
                atorNetGames.enviaJogada(jogada);
            }
        });
        
        btnDesistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int desistiu = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja desistir da partida?");
                if(desistiu == 0){
                    
                    atorNetGames.finalizarPartida();
                    mapa.finalizaPartida();
                    mapa.exibirMensagem("Você desistiu da partida");
                    finalizaJogo();
                    atorNetGames.enviaJogada(new JogadaMapa(mapa.getJogadorMapa(), null, TipoJogada.DESISTIR));
                }
            }
        });
        
        btnDesconectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int desistiu = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja desistir da partida?");
                if(desistiu == 0){   
                    try {
                        atorNetGames.desconectar();
                        desconectar();
                    } catch (NaoConectadoException ex) {
                        mapa.exibirMensagem("Você não esta conectado a uma partida");
                    }
                    
                }
            }
        });
    }
    
    //colhe os parâmetros para se conectar ao cervidor Proxy
    public boolean conectar(){
        String nome = "";
        String racaString = "";
        //nome = JOptionPane.showInputDialog("Insira seu nome");
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
                racaString = "Elfo";
            break;
            case 1:
                racaString = "Humano";
            break;
            case 2:
                racaString = "Uruk Hai";
            break;
            case 3:
                racaString = "Orc";
            break;
        }
        
        servidor = JOptionPane.showInputDialog("Insira o endereço do servidor no qual deseja se conectar", "netgames.labsoft.ufsc.br");
        try {
            
            atorNetGames.conectar(racaString, servidor);
            mapa.setJogadorMapa(new Jogador(Jogador.pegaRacaPeloNome(racaString)));
            //infoNomeJogador.setText("Jogador: " + nome);
            infoRacaJogador.setText("Raça: " + racaString);
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void desconectar(){             
        btnIniciaJogo.setVisible(false);
        btnDesconectar.setVisible(false);
        btnConectarServidor.setVisible(true);
    }
    
    public void iniciaJogo(){
        mapa.iniciaPartida();
        btnIniciaJogo.setVisible(false);
        btnDesconectar.setVisible(false);
        btnDesistir.setVisible(true);
        setaValorPosicao(mapa.getGollum().getPosicao().getX(), mapa.getGollum().getPosicao().getY(), mapa.getGollum());
        infoRecursos.setText("Recursos: " + mapa.getJogadorMapa().getCidade().getRecursos());
    }
    
    //Limpar Mapa
    public void finalizaJogo(){
        mapa.finalizaPartida();
        
        for(int i = 0; i< mapa.getTamX(); i++){
            for(int j = 0; j< mapa.getTamY(); j++){
                tabela.setValueAt(null, i, j);
            }
        }
        btnPassaVez.setVisible(false);
        btnDesistir.setVisible(false);
        btnDesconectar.setVisible(true);
        btnIniciaJogo.setVisible(true);
        infoRacaJogador.setText("");
        infoRecursos.setText("");
        infoNomeJogador.setText("");
        
    }
    
    //atualiza a tela com a jogada recebida
    public void recebeJogada(JogadaMapa jogada){
        if(jogada != null){
            switch(jogada.getTipoJogada()){

                case ATACAR:
                    System.out.println("Atacar");
                    Tropa atacante = (Tropa) jogada.getAtual();
                    
                    //saga do anel
                    if(jogada.getAlvo().getClass() == Gollum.class){
                        Heroi heroi = (Heroi)jogada.getAtual();
                        Gollum gollum = (Gollum) jogada.getAlvo();
                        setaValorPosicao(heroi.getPosicao(), heroi);
                        setaValorPosicao(gollum.getPosicao(), gollum);
                        if(!gollum.temAnel()){
                            Mago mago = new Mago(posicaoLivreMaisProxima(heroi.getPosicao()), heroi.getCidade());
                            setaValorPosicao(mago.getPosicao(), mago);
                        }
                        
                    }
                    
                    setaValorPosicao(atacante.getPosicao(), atacante);
                    if(jogada.getAlvo().getClass().getSuperclass() == Construcao.class){
                        Construcao alvo = (Construcao) jogada.getAlvo();
                        setaValorPosicao(alvo.getPosicao(), alvo);
                        if(alvo.isDestruido() && mapa.getJogadorInimigo().getCidade() == alvo.getCidade()){
                            if(mapa.getJogadorInimigo().getCidade().verificaTodosDestruidos()){
                                JogadaMapa jogadaEnvia = new JogadaMapa(mapa.getJogadorInimigo(), null, TipoJogada.DERROTADO);
                                mapa.getJogadorMapa().setVencedor(true);
                                atorNetGames.enviaJogada(jogadaEnvia);
                                recebeJogada(jogadaEnvia);
                            }
                        }

                    }else if(jogada.getAlvo().getClass().getSuperclass() == Tropa.class){
                        Tropa alvo = (Tropa) jogada.getAlvo();
                        setaValorPosicao(alvo.getPosicao(), alvo);  
                    }
                break;

                case REFORMA_CONSTRUCAO:
                    System.out.println("Reformar construção");
                    Construcao reformada = (Construcao) jogada.getAlvo();
                    setaValorPosicao(reformada.getPosicao(), reformada);
                break;

                case NOVA_TROPA:
                    System.out.println("Nova tropa");
                    Tropa novaTropa = (Tropa) jogada.getAlvo();
                    Construcao cons = (Construcao) jogada.getAtual();
                    Posicao posTropa = posicaoLivreMaisProxima(cons.getPosicao());
                    novaTropa.setPosicaoAtual(posTropa);
                    setaValorPosicao(posTropa, novaTropa);      
                break;

                case MOVIMENTAR:
                    System.out.println("Movimentar");
                    Posicao antiga = (Posicao) jogada.getAtual();
                    if(jogada.getAlvo().getClass().getSuperclass() == Tropa.class){
                        System.out.println("Tropa");
                        Tropa atual = (Tropa) jogada.getAlvo();
                        System.out.println(atual.getPosicao().getX());
                        System.out.println(atual.getPosicao().getY());
                        setaValorPosicao(atual.getPosicao(), atual);
                    }else if(jogada.getAlvo().getClass() == Gollum.class){
                        System.out.println("Gollum");
                        mapa.setGollum((Gollum) jogada.getAlvo());
                        setaValorPosicao(mapa.getGollum().getPosicao(), mapa.getGollum());
                    }
                    setaValorPosicao(antiga, null);
                break;
                
                case PASSA_VEZ:
                     System.out.println("Passar Vez");
                    Jogador passouVez = (Jogador) jogada.getAtual();
                    //verifica se passou um turno completo
                    /*if(passouVez.getVezJogada() == 1 ){
                        movimentarGollum();
                        mapa.getGollum().agir();
                    }*/
                    //verifica se e a vez do jogador
                    if(passouVez != mapa.getJogadorMapa()){
                        mapa.getJogadorMapa().recebeVez();
                        btnPassaVez.setVisible(true);
                    }
                break;
                
                case DERROTADO:
                    if((Jogador)jogada.getAtual() == mapa.getJogadorMapa()){
                        mapa.exibirMensagem("Voce perdeu todas as suas construções e foi derrotado");
                    }else{
                        mapa.exibirMensagem("Voce destruiu todas as construções de seu inimigo e venceu a partida");
                    }
                    finalizaJogo();
                    atorNetGames.finalizarPartida();
                break;
                    
                case DESISTIR:
                    if((Jogador)jogada.getAtual()!= mapa.getJogadorMapa()) 
                        mapa.exibirMensagem("Seu adversário desistiu da partida, Voce venceu");
                    atorNetGames.finalizarPartida();    
                    finalizaJogo();
                break;

                default:
                    System.out.println("jogada nao identificada");
                break;
            }
        }else System.out.println("jogada nula");
        infoRecursos.setText("Recursos: " + mapa.getJogadorMapa().getCidade().getRecursos()); 
    }
    
    //seta que jogadorMapa e o primeiro a jogar
    public void souPrimeiroAJogar(String racaAdversario){
        mapa.getJogadorMapa().recebeVez();
        mapa.getJogadorMapa().setVezJogada(1);
        posicionaJogadoresNoMapa(1, mapa.getJogadorMapa());

        mapa.setJogadorInimigo(new Jogador(Jogador.pegaRacaPeloNome(racaAdversario)));
        mapa.getJogadorMapa().setVezJogada(2);
        posicionaJogadoresNoMapa(2, mapa.getJogadorInimigo());
        iniciaJogo();
        mapa.exibirMensagem("Você foi conectado a uma partida");
    }
    
    //seta que jogadorMapa e o segundo a jogar
    public void souSegundoAJogar(String racaAdversario){
        mapa.getJogadorMapa().setVezJogada(2);
        posicionaJogadoresNoMapa(2, mapa.getJogadorMapa());

        mapa.setJogadorInimigo(new Jogador(Jogador.pegaRacaPeloNome(racaAdversario)));
        mapa.getJogadorMapa().setVezJogada(1);
        posicionaJogadoresNoMapa(1, mapa.getJogadorInimigo());
        iniciaJogo();
        mapa.exibirMensagem("Você foi conectado a uma partida");
    }
    
    //movimenta o Gollum na tela
    public void movimentarGollum(){
        //TODO: refazer
        JogadaMapa jogada = new JogadaMapa(
                new Posicao(
                        mapa.getGollum().getPosicao().getX(), mapa.getGollum().getPosicao().getY()
                ),
                mapa.getGollum(),
                TipoJogada.MOVIMENTAR
        );
        mapa.getGollum().setPosicao(posicaoLivreMaisProxima(mapa.getGollum().getPosicao()));
        atorNetGames.enviaJogada(jogada);
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
    
    //retorna a posicao livre mais proxima da posicao passada
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
    
    //posiciona jogadores no mapa
    public void posicionaJogadoresNoMapa(int ordemJogador, Jogador jogador){
       // for(int i = 0; i< qtdeJogadores; i++){
            //posicina edificio principal
        Posicao temp = mapa.pegaPosicaoInicialJogadoresNoMapa().get(--ordemJogador);
        setaValorPosicao(
                temp, 
                jogador.getCidade().construir(Principal.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
        );

        //posiciona Arquearia
        if(temp.getX() > 2) temp.setX(temp.getX() - 2);
        else temp.setX(temp.getX() + 2);

        setaValorPosicao(
                temp, 
                jogador.getCidade().construir(Arquearia.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
        );

        //posiciona Estabulo
        if(temp.getY() > 2) temp.setY(temp.getY() - 2);
        else temp.setY(temp.getY() + 2);     

        setaValorPosicao(
                temp, 
                jogador.getCidade().construir(Estabulo.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
        );

        //posiciona Quartel
        if(temp.getX() > 4 )temp.setX(temp.getX() + 2);
        else temp.setX(temp.getX() - 2); 

        setaValorPosicao(
                temp, 
                jogador.getCidade().construir(Quartel.class.getSimpleName(), new Posicao(temp.getX(), temp.getY()))
        );
        //}
        
        
    }

}
      