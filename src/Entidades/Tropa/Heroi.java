/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Gollum.Anel;
import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Heroi extends Tropa{
    private String simboloNormal = "✩";
    private String simboloAnel = "✮";
    private Anel anel;
    private int acertosCharada;
    private boolean duelando;


    private enum tipoHeroi{
        NORMAL, ULTRA;
    }
    
    public Heroi(Posicao posicao){
        super(25, 1, 10, 1, 5, "✩", posicao);
        this.anel = null;
        this.acertosCharada = 0;
        this.duelando = false;
    }
    
    public Anel perdeAnel() {
        this.anel = null;
        return new Anel();
    }

    public void pegaAnel(Anel anel) {
        this.anel = anel;
    }

    public int getAcertosCharada() {
        return acertosCharada;
    }

    public void setAcertosCharada(int acertosCharada) {
        this.acertosCharada = acertosCharada;
    }

    public boolean isDuelando() {
        return duelando;
    }

    public void setDuelando(boolean duelando) {
        this.duelando = duelando;
    }
}


