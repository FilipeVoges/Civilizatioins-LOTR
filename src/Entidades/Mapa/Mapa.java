/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Mapa;

import Entidades.Cidade.Cidade;
import Entidades.Construcao.Arquearia;
import Entidades.Construcao.Construcao;
import Entidades.Construcao.Estabulo;
import Entidades.Construcao.Principal;
import Entidades.Construcao.Quartel;
import Entidades.Gollum.Charada;
import Entidades.Gollum.Gollum;
import Entidades.Jogada.JogadaMapa;
import Entidades.Jogador.Jogador;
import Entidades.Tropa.Arqueiro;
import Entidades.Tropa.Cavaleiro;
import Entidades.Tropa.Espadachim;
import Entidades.Tropa.Heroi;
import Entidades.Tropa.Mago;
import Entidades.Tropa.Tropa;
import Enumeradores.TipoJogada;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author filipe
 */

public class Mapa implements Serializable {
    
    protected int tamX;
    protected int tamY;
    protected ArrayList<Cidade> cidades;
    protected ArrayList<Posicao> posInicialJogadoresNoMapa;
    protected Gollum gollum;
    protected boolean partidaEmAndamento;
    protected Object objetoSelecionado;
    protected Jogador jogadorMapa, jogadorInimigo;
    protected ArrayList<Tropa> tropasMovimentadasNaRodada;
    protected ArrayList<Tropa> tropasAtacantesNaRodada;
    
    public Mapa() {
       
        this.tamX = 25;
        this.tamY = 25;
        iniciaPosicoesMapa();
        this.cidades = new ArrayList<>();
        this.gollum = new Gollum(new Posicao(tamX / 2, tamY / 2));
        this.partidaEmAndamento = false;
        this.tropasAtacantesNaRodada = new ArrayList<>();
        this.tropasMovimentadasNaRodada = new ArrayList<>();
    }
    
    private void iniciaPosicoesMapa(){
        posInicialJogadoresNoMapa = new ArrayList<>();
        posInicialJogadoresNoMapa.add(new Posicao(1, 1));
        posInicialJogadoresNoMapa.add(new Posicao(tamX -2, tamY -2));
        posInicialJogadoresNoMapa.add(new Posicao(1, tamY -2));
        posInicialJogadoresNoMapa.add(new Posicao(tamX -2 , 1));  
    }
   
    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public boolean verificaPartidaEmAndamento() {
        return partidaEmAndamento;
    }
    
    public void iniciaPartida() {
        partidaEmAndamento = true;
    }
    
    public void finalizaPartida() {
        partidaEmAndamento = false;
    }

    public Gollum getGollum() {
        return gollum;
    }

    public void setGollum(Gollum gollum) {
        this.gollum = gollum;
    }

    public ArrayList<Posicao> pegaPosicaoInicialJogadoresNoMapa() {
        return posInicialJogadoresNoMapa;
    }
    
    public void exibirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public Jogador getJogadorMapa() {
        return jogadorMapa;
    }

    public void setJogadorMapa(Jogador jogadorMapa) {
        this.jogadorMapa = jogadorMapa;
    }

    public Jogador getJogadorInimigo() {
        return jogadorInimigo;
    }

    public void setJogadorInimigo(Jogador jogadorInimigo) {
        this.jogadorInimigo = jogadorInimigo;
    }
    
    public void novoTurno(){
        this.tropasAtacantesNaRodada.clear();
        this.tropasMovimentadasNaRodada.clear();
        this.objetoSelecionado = null;
    }
    
    public JogadaMapa realizaJogada(Posicao clique, Jogador jogador, Object objeto){
        
        JogadaMapa jogada = null;
        
        if(partidaEmAndamento){
            switch(jogador.getTipoClique()){

                case SELECAO:

                    if(objeto != null){
                        if(objeto.getClass().getSuperclass() == Construcao.class){
                            jogada = cliqueConstrucao(objeto, jogador);
                        }
                        else if(objeto.getClass().getSuperclass() == Tropa.class){
                            cliqueTropa(objeto, jogador);
                        }
                        else if(objeto.getClass() == Gollum.class){
                            exibirMensagem("Meu precioso!!");
                        }                 
                    }
                break;

                case ATACAR:
                    if(objeto != null && objetoSelecionado != null){
                        jogada = atacar(objeto, jogador); 
                    }else{
                        exibirMensagem("Selecione um alvo válido");
                        objetoSelecionado = null;
                    }
                    jogador.setTipoClique(TipoJogada.SELECAO);
                break;

                case MOVIMENTAR:

                    if(objeto == null){
                        Tropa tropaSelecionada = (Tropa) objetoSelecionado;
                        
                        if(tropasMovimentadasNaRodada.contains(tropaSelecionada)){
                            exibirMensagem("Você já movimentou essa tropa na rodada");
                            jogador.setTipoClique(TipoJogada.SELECAO); 
                            break;
                        }
                        
                        Posicao atual = new Posicao(tropaSelecionada.getPosicao().getX(), tropaSelecionada.getPosicao().getY());
                        int distancia = tropaSelecionada.calculaDistancia(clique);
                        if(distancia <= tropaSelecionada.getDistanciaMovimento()){
                            tropaSelecionada.setPosicaoAtual(new Posicao(clique.getX(), clique.getY()));
                            jogada = new JogadaMapa(atual, tropaSelecionada, TipoJogada.MOVIMENTAR);
                            tropasMovimentadasNaRodada.add(tropaSelecionada);
                            jogador.setTipoClique(TipoJogada.SELECAO);
                            objetoSelecionado = null;
                        }else{
                            exibirMensagem("Sua distância máxima de movimento é " + tropaSelecionada.getDistanciaMovimento() + " campos com essa tropa.");
                            jogador.setTipoClique(TipoJogada.SELECAO);
                        }
                    }else{
                        exibirMensagem("Selecione uma posição disponível");
                        objetoSelecionado = null;
                        jogador.setTipoClique(TipoJogada.SELECAO);
                    }
                break;
            }
        }else exibirMensagem("Não existe partida em andamento");
        
        return jogada;
    }
    
    /**************************************************************************/
    private JogadaMapa cliqueConstrucao(Object objeto, Jogador jogador){
        JogadaMapa jogada = null;
        Construcao construcao = (Construcao) objeto;
        String[] options = new String[] {"Nova Tropa", "Reformar", "Cancelar"};
            
        if(construcao.getCidade() == jogador.getCidade() && jogador.verificaVez() && !construcao.isDestruido()){
            int response = JOptionPane.showOptionDialog(
                null, 
                "Vida: " + construcao.getVida()+ "\n Selecione o que você deseja fazer", 
                objeto.getClass().getSimpleName(),
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null, 
                options, 
                options[0]
            );

            int r;
            switch(response){
                /***********************NOVA TROPA*******************************/
                case 0://nova tropa
                    r = JOptionPane.showConfirmDialog(null, "Preço: " + construcao.getRecursoRecrutamento() + "\n Deseja realmente recrutar essa tropa?");
                    if(r == 0){
                        if(jogador.getCidade().descontaRecursos(construcao.getRecursoRecrutamento())){
                            jogada = new JogadaMapa();
                            jogada.setTipoJogada(TipoJogada.NOVA_TROPA);
                            jogada.setAtual(construcao);
                            
                            if(objeto.getClass() == Arquearia.class){ 
                                jogada.setAlvo(new Arqueiro(construcao.getPosicao(), jogador.getCidade()));
                            }else if(objeto.getClass() == Quartel.class){
                                jogada.setAlvo(new Espadachim(construcao.getPosicao(), jogador.getCidade()));
                            }else if(objeto.getClass() == Estabulo.class){
                                jogada.setAlvo(new Cavaleiro(construcao.getPosicao(), jogador.getCidade()));
                            }else if(objeto.getClass() == Principal.class){
                                Principal principal = (Principal) objeto;
                                if(!principal.isHeroiConjurado()){
                                    jogada.setAlvo(principal.recrutar(construcao.getPosicao()));
                                    principal.setHeroiConjurado(true);
                                }else{
                                    exibirMensagem("Voce ja possui um heroi");
                                    jogador.setTipoClique(TipoJogada.SELECAO);
                                }
                                
                            }
                        }else{
                            exibirMensagem("Recursos Insuficientes");
                            jogador.setTipoClique(TipoJogada.SELECAO);
                        }
                    }
                break;

                /***********************REFORMAR*******************************/
                case 1://reformar
                    r = JOptionPane.showConfirmDialog(null, "Preço: " + construcao.calculaReforma() + "\n Deseja realmente reformar essa construção?");
                    if(r == 0){
                        if(jogador.getCidade().descontaRecursos(construcao.calculaReforma())){
                            
                            if(construcao.reformar()){
                                jogada = new JogadaMapa();
                                jogada.setTipoJogada(TipoJogada.REFORMA_CONSTRUCAO);
                                jogada.setAtual(construcao);
                                jogada.setAlvo(construcao);
                            }else{
                                exibirMensagem("Não é possível reformar uma construção destruida");
                                jogador.setTipoClique(TipoJogada.SELECAO);
                            }
                        
                        }else{
                            exibirMensagem("Recursos Insuficientes");
                            jogador.setTipoClique(TipoJogada.SELECAO);
                        }
                    }
                        
                    
                break;

                case 2://cancelar
                break;
            }
            
        }else JOptionPane.showMessageDialog(
                null, 
                "Cidade: " + construcao.getCidade().getNome()
                 + "\nVida: " + construcao.getVida(), 
                objeto.getClass().getSimpleName(), 
                0
              );
        
        return jogada;
    }
     
   /**************************************************************************/
    private void cliqueTropa(Object objeto, Jogador jogador){
        Tropa tropa = (Tropa) objeto;
                 
        if(tropa.getCidade() == jogador.getCidade() && jogador.verificaVez() && tropa.isVivo()){
            String[] options = new String[] {"Atacar", "Movimentar", "Cancelar"};

            int response = JOptionPane.showOptionDialog(
                null,
                "Cidade: " + tropa.getCidade().getNome()
                + "\nVida: " + tropa.getVida()+ "\n"
                + "Selecione o que você deseja fazer", 
                objeto.getClass().getSimpleName(),
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null, 
                options, 
                options[0]
            );

             switch(response){
                case 0://atacar
                    jogador.setTipoClique(TipoJogada.ATACAR);
                    objetoSelecionado = objeto;
                break;

                case 1://movimentar
                    jogador.setTipoClique(TipoJogada.MOVIMENTAR);
                    objetoSelecionado = objeto;
                break;

                case 2://cancelar
                    jogador.setTipoClique(TipoJogada.SELECAO);
                break;
            }
        }else JOptionPane.showMessageDialog(
                null, 
                "Cidade: " + tropa.getCidade().getNome()
                 + "\nVida: " + tropa.getVida(), 
                objeto.getClass().getSimpleName(), 
                0
              );   
    }

    /**************************************************************************/
    private JogadaMapa atacar(Object objeto, Jogador jogador){
       
        JogadaMapa jogada = null;
        
        if(tropasAtacantesNaRodada.contains((Tropa) objetoSelecionado)){
            exibirMensagem("Você já atacou com essa tropa na rodada");
            jogador.setTipoClique(TipoJogada.SELECAO); 
            return null;
        }
        
        if(objeto.getClass().getSuperclass() == Construcao.class){
            Construcao alvo = (Construcao) objeto;
            Tropa atacante = (Tropa) objetoSelecionado;
            
            System.out.println(atacante.calculaDistancia(alvo.getPosicao()));
            System.out.println(atacante.getDistanciaAtaque());
            
            if(atacante.getCidade() == alvo.getCidade()){
                exibirMensagem("Você não pode atacar suas próprias construções");
                jogador.setTipoClique(TipoJogada.SELECAO); 
                return null;
            }
            
            //calcula distancia
            if(atacante.calculaDistancia(alvo.getPosicao()) - atacante.getDistanciaAtaque() <= 0){
                alvo.recebeDano(atacante.calculaDano(alvo));
                jogada = new JogadaMapa(atacante, alvo, TipoJogada.ATACAR);
                tropasAtacantesNaRodada.add(atacante);
                
            }else exibirMensagem("Você esta muito distante do alvo para atacar");
            
            
        }else if(objeto.getClass().getSuperclass() == Tropa.class){
            Tropa alvo = (Tropa) objeto;
            Tropa atacante = (Tropa) objetoSelecionado;
            
            
            if(atacante.getCidade() == alvo.getCidade()){
                exibirMensagem("Você não pode atacar suas próprias tropas");
                jogador.setTipoClique(TipoJogada.SELECAO); 
                return null;
            }
            
            //calcula distancia
            if(atacante.calculaDistancia(alvo.getPosicao()) - atacante.getDistanciaAtaque() <= 0){
                
                alvo.recebeDano(atacante.calculaDano(alvo));
                if(alvo.isVivo())atacante.recebeDano(alvo.calculaRetalicao(atacante));
                jogada = new JogadaMapa(atacante, alvo, TipoJogada.ATACAR);
                tropasAtacantesNaRodada.add(atacante);
             
            }else exibirMensagem("Você esta muito distante do alvo para atacar");

        }
        else if(objeto.getClass() == Gollum.class){
            if(objetoSelecionado != null && objetoSelecionado.getClass() == Heroi.class){
                Heroi heroiSelecionado = (Heroi) objetoSelecionado;
                if(heroiSelecionado.calculaDistancia(gollum.getPosicao()) - heroiSelecionado.getDistanciaAtaque() <= 0 && gollum.temAnel()){
                    exibirMensagem("Vou roubar teu precioso");
                    
                    ArrayList<Charada> charadas = gollum.mostraCharada();
                    
                    for( int i = 0; i < 3; i++ ){
                        System.out.println(i);
                        String[] options = new String[] {
                            charadas.get(i).getRespostaErrada1(), 
                            charadas.get(i).getRespostaCerta(), 
                            charadas.get(i).getRespostaErrada2()};
                        int response = JOptionPane.showOptionDialog(
                            null,
                            charadas.get(i).getPergunta(),
                            "Charada " + i,
                            JOptionPane.DEFAULT_OPTION, 
                            JOptionPane.PLAIN_MESSAGE,
                            null, 
                            options, 
                            options[0]
                        );
                        
                        if(response == 1){
                            //System.out.println("Acertou mizeravi");

                            heroiSelecionado.setAcertosCharada(heroiSelecionado.getAcertosCharada()+1);
                        }else break;
                    }
                    System.out.println(heroiSelecionado.getAcertosCharada());
                    if(heroiSelecionado.getAcertosCharada() == 3){
                        exibirMensagem("Você venceu o duelo com o Gollum e ganhou o apoio de um mago em sua jornada");
                        heroiSelecionado.pegaAnel(gollum.perdeAnel());
                        jogada = new JogadaMapa(heroiSelecionado, gollum, TipoJogada.ATACAR);
                    }else{
                        exibirMensagem("Você errou, agora eu vou te comer!!");
                        gollum.comer(heroiSelecionado);
                        jogada = new JogadaMapa(heroiSelecionado, gollum, TipoJogada.ATACAR);
                    }
                    
                    gollum.setVisivel(false);
                    
                }else exibirMensagem("Você não pode atacar o Gollum");
            }else exibirMensagem("A Tropa selecionada não pode atacar o Gollum");
              
        }
        return jogada;
        
    }
    
}
