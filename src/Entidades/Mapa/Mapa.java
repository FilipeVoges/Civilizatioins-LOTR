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
import Entidades.Gollum.Gollum;
import Entidades.Jogada.Jogada;
import Entidades.Jogador.Jogador;
import Entidades.Tropa.Arqueiro;
import Entidades.Tropa.Cavaleiro;
import Entidades.Tropa.Espadachim;
import Entidades.Tropa.Heroi;
import Entidades.Tropa.Tropa;
import Enumeradores.TipoJogada;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author filipe
 */

public class Mapa {
    
    protected int tamX;
    protected int tamY;
    protected ArrayList<Cidade> cidades;
    protected ArrayList<Posicao> posInicialMapa;
    protected Gollum gollum;
    boolean partidaEmAndamento;
    protected Tropa tropaSelecionada;
    //Falta a collection de jogadores definidas no Mapa
    
    public Mapa(){
       
        this.tamX = 25;
        this.tamY = 25;
        iniciaPosicoesMapa();
        cidades = new ArrayList<>();
        gollum = new Gollum(new Posicao(tamX / 2, tamY / 2));
        this.partidaEmAndamento = false;
    }
    
    private void iniciaPosicoesMapa(){
        posInicialMapa = new ArrayList<>();
        posInicialMapa.add(new Posicao(tamX -2 , 1));
        posInicialMapa.add(new Posicao(1, tamY -2));
        posInicialMapa.add(new Posicao(tamX -2, tamY -2));
        posInicialMapa.add(new Posicao(1, 1));
    }
    
    //Falta uma caralhada de função. Verificar no diagrama de classes
    public void iniciarPartida(){
        if(!verificaPartidaEmAndamento()){
            this.partidaEmAndamento = true;
            //TODO: fazer o iniciar partida com o NetGames NO DIAGRAMA ESTA RETORNANDO BOOLEAN
        }
    }
    
    public void informarDesistencia(Jogador jogador){
        
        for(Cidade cidade :cidades){
            if(jogador.getCidade() == cidade){
                cidades.remove(cidade);
            }
        }
        //TODO: enviar desistencia pro NetGames
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

    public void setCidades(ArrayList<Cidade> cidades) {
        this.cidades = cidades;
    }

    public boolean verificaPartidaEmAndamento() {
        return partidaEmAndamento;
    }
    
     public void setPartidaEmAndamento(boolean partidaEmAndamento) {
        this.partidaEmAndamento = partidaEmAndamento;
    }

    public Gollum getGollum() {
        return gollum;
    }

    public ArrayList<Posicao> getPosInicialMapa() {
        return posInicialMapa;
    }
    
    public void exibirMensagem(String mensagem){
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    
    public Jogada realizaJogada(Posicao clique, Jogador jogador, Object objeto){
        
        Jogada jogada = null;
        
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
                        JOptionPane.showMessageDialog(null, "Meu precioso!!", "Gollum", 0);
                    }                 
                }
            break;
            
            case ATACAR:
                if(objeto != null){
                    //TODO: Implementar lógica
                }else{
                    exibirMensagem("Selecione um alvo válido");
                    jogador.setTipoClique(TipoJogada.SELECAO);
                    tropaSelecionada = null;
                }
            break;
            
            case MOVIMENTAR:
                if(objeto == null){
                    Posicao atual = new Posicao(tropaSelecionada.getPosicao().getX(), tropaSelecionada.getPosicao().getY());
                    int distancia = tropaSelecionada.calculaDistancia(clique);
                    if(distancia <= tropaSelecionada.getDistanciaMovimento()){
                        tropaSelecionada.setPosicaoAtual(clique);
                        jogada = new Jogada(atual, tropaSelecionada, TipoJogada.MOVIMENTAR);
                        jogador.setTipoClique(TipoJogada.SELECAO);
                        tropaSelecionada = null;
                    }
                }else{
                    exibirMensagem("Selecione uma posição disponível");
                    jogador.setTipoClique(TipoJogada.SELECAO);
                    tropaSelecionada = null;
                }
            break;
        }
        
        return jogada;
    }
    
    /**************************************************************************/
    private Jogada cliqueConstrucao(Object o, Jogador jogador){
        Jogada jogada = null;
        Construcao construcao = (Construcao) o;
        String[] options = new String[] {"Nova Tropa", "Reformar", "Cancelar"};
            
        if(construcao.getCidade() == jogador.getCidade() && jogador.verificaVez()){
            int response = JOptionPane.showOptionDialog(
                null, 
                "Vida: " + construcao.getVida()+ "\n Selecione o que você deseja fazer", 
                o.getClass().getSimpleName(),
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
                            jogada = new Jogada();
                            jogada.setTipoJogada(TipoJogada.NOVA_TROPA);
                            jogada.setAntigo(construcao);
                            
                            if(o.getClass() == Arquearia.class){ 
                                jogada.setModificado(new Arqueiro(construcao.getPosicao(), jogador.getCidade()));
                            }else if(o.getClass() == Quartel.class){
                                jogada.setModificado(new Espadachim(construcao.getPosicao(), jogador.getCidade()));
                            }else if(o.getClass() == Estabulo.class){
                                jogada.setModificado(new Cavaleiro(construcao.getPosicao(), jogador.getCidade()));
                            }else if(o.getClass() == Principal.class){
                                Principal principal = (Principal) o;
                                if(!principal.isHeroiConjurado()){
                                    jogada.setModificado(principal.recrutar(construcao.getPosicao()));
                                    principal.setHeroiConjurado(true);
                                }else exibirMensagem("Voce ja possui um heroi");
                                
                            }
                        }else exibirMensagem("Recursos Insuficientes");
                    }
                break;

                /***********************REFORMAR*******************************/
                case 1://reformar
                    r = JOptionPane.showConfirmDialog(null, "Preço: " + construcao.calculaReforma() + "\n Deseja realmente reformar essa construção?");
                    if(r == 0){
                        if(jogador.getCidade().descontaRecursos(construcao.calculaReforma())){
                               
                            jogada = new Jogada();
                            jogada.setTipoJogada(TipoJogada.REFORMA_CONSTRUCAO);
                            jogada.setAntigo(construcao);
                            construcao.reformar();
                            jogada.setModificado(construcao);
                        
                        }else exibirMensagem("Recursos Insuficientes");
                    }
                        
                    
                break;

                case 2://cancelar
                break;
            }
            
        }else JOptionPane.showMessageDialog(
                null, 
                "Cidade: " + construcao.getCidade().getNome()
                 + "\nVida: " + construcao.getVida(), 
                o.getClass().getSimpleName(), 
                0
              );
        
        return jogada;
    }
     
   /**************************************************************************/
    private void cliqueTropa(Object o, Jogador jogador){
        Tropa tropa = (Tropa) o;
                 
        if(tropa.getCidade() == jogador.getCidade() && jogador.verificaVez()){
            String[] options = new String[] {"Atacar", "Movimentar", "Cancelar"};

            int response = JOptionPane.showOptionDialog(
                null,
                "Cidade: " + tropa.getCidade().getNome()
                + "\nVida: " + tropa.getVida()+ "\n"
                + "Selecione o que você deseja fazer", 
                o.getClass().getSimpleName(),
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null, 
                options, 
                options[0]
            );

             switch(response){
                case 0://atacar
                    jogador.setTipoClique(TipoJogada.ATACAR);
                    tropaSelecionada = tropa;
                break;

                case 1://movimentar
                    jogador.setTipoClique(TipoJogada.MOVIMENTAR);
                    tropaSelecionada = tropa;
                break;

                case 2://cancelar
                    jogador.setTipoClique(TipoJogada.SELECAO);
                break;
            }
        }else JOptionPane.showMessageDialog(
                null, 
                "Cidade: " + tropa.getCidade().getNome()
                 + "\nVida: " + tropa.getVida(), 
                o.getClass().getSimpleName(), 
                0
              );   
    }
    
    
    
    
    
}
