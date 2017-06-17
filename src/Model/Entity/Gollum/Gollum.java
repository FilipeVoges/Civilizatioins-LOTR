/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Gollum;

/**
 *
 * @author filipe
 */
public class Gollum {
    private Anel anel;
    private boolean visivel;
    private boolean duelando;
    private String simbolo = "¤";
    
    public Gollum(){
        this.anel = new Anel();
        this.visivel = true;
    }
    
     public Anel perdeAnel() {
        this.anel = null;
        return new Anel();
    }

    public void pegaAnel(Anel anel) {
        this.anel = anel;
    }
    
    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public boolean isDuelando() {
        return duelando;
    }

    public void setDuelando(boolean duelando) {
        this.duelando = duelando;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
