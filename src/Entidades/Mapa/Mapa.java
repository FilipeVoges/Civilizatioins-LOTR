/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Mapa;

import Entidades.Cidade.Cidade;
import Entidades.Gollum.Gollum;
import Entidades.Jogador.Jogador;
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
    
    
    
    
    
    
    
    
}
