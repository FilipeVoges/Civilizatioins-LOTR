/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Tropa;

import Model.Entity.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Tropa {
    
    private int id;
    private int vida;
    private int velocidadeMovimento;
    private int forca;
    private int distanciaAtaque;
    private Posicao posicao;
    private String simbolo;

    public Tropa(int vida, int velocidadeMovimento, int forca, int distanciaAtaque, String simbolo) {
        this.vida = vida;
        this.velocidadeMovimento = velocidadeMovimento;
        this.forca = forca;
        this.distanciaAtaque = distanciaAtaque;
        this.simbolo = simbolo;
    }
    
    public int getVida() {
        return vida;
    }

    public int getVelocidadeMovimento() {
        return velocidadeMovimento;
    }

    public int getForca() {
        return forca;
    }

    public int getDistanciaAtaque() {
        return distanciaAtaque;
    }

    public Posicao getPosicao() {
        return posicao;
    }
	
      
    private int calculaDistancia(Tropa tropaAliada, Posicao destino){
        Posicao posicaoAtual = tropaAliada.getPosicao();
        
        int posY = Math.abs(posicaoAtual.getY() - destino.getY());
        int posX = Math.abs(posicaoAtual.getX() - destino.getX());
        
        return posY + posX; 
    }
    
    private int calculaDano(Tropa tropaAtacante){
        return (int) Math.round(tropaAtacante.getForca() * Math.random());
    }
    
    private int calculaRetalicao(Tropa tropaAtacante){
        //return (int) Math.round((getForca() + getVida())  * Math.random());
        return 0;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
