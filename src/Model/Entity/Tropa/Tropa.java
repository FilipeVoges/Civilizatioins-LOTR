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
    private int diatanciaAtaque;
    private Posicao posicao;

    public Tropa(int vida, int velocidadeMovimento, int forca, int diatanciaAtaque) {
        this.vida = vida;
        this.velocidadeMovimento = velocidadeMovimento;
        this.forca = forca;
        this.diatanciaAtaque = diatanciaAtaque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidadeMovimento() {
        return velocidadeMovimento;
    }

    public void setVelocidadeMovimento(int velocidadeMovimento) {
        this.velocidadeMovimento = velocidadeMovimento;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDiatanciaAtaque() {
        return diatanciaAtaque;
    }

    public void setDiatanciaAtaque(int diatanciaAtaque) {
        this.diatanciaAtaque = diatanciaAtaque;
    }

    public Posicao getPosicao() {
        return posicao;
    }

    public void setPosicao(Posicao posicao) {
        this.posicao = posicao;
    }
	
	private int calculaDistancia(Tropa tropaAliada, Posicao destino){
        Posicao posicaoAtual = tropaAliada.getPosicao();
        
        int posY = Math.abs(posicaoAtual.getY() - destino.getY());
        int posX = Math.abs(posicaoAtual.getX() - destino.getX());
        
        return posY + posX; 
    }
    
    private int calculaRetalicao(){
        return 0;
    }
    
}
