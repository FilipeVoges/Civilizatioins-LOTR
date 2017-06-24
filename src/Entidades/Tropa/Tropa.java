/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Tropa {
    
    private int vida;
    private int velocidadeMovimento;
    private int forca;
    private int distanciaAtaque;
    private Posicao posicaoAtual;
    private Posicao posicaoDestino;
    private int resistencia;
    private String simbolo;
    private Cidade cidade;

    public Tropa(
            int vida, 
            int velocidadeMovimento, 
            int forca, 
            int distanciaAtaque, 
            int resistencia, 
            String simbolo, 
            Posicao posicao,
            Cidade cidade
    ) {
        this.vida = vida;
        this.velocidadeMovimento = velocidadeMovimento;
        this.forca = forca;
        this.distanciaAtaque = distanciaAtaque;
        this.resistencia = resistencia;
        this.simbolo = simbolo;
        this.posicaoAtual = posicao;
        this.cidade = cidade;
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
    
    public int getResistencia() {
        return resistencia;
    }

    public Posicao getPosicao() {
        return posicaoAtual;
    }

    public Posicao getPosicaoDestino() {
        return posicaoDestino;
    }

    public void setPosicaoDestino(Posicao posicaoDestino) {
        this.posicaoDestino = posicaoDestino;
    }

    public Cidade getCidade() {
        return cidade;
    }

    protected void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
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
    
    private int calculaRetalicao(){
        return (int) Math.round(getResistencia() * Math.random());
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
