/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Construcao;

import Model.Entity.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Construcao {
    
    private Posicao posicao;
    private int vida;
    private final String simbolo;
    
    public Construcao(String simbolo, Posicao pos){
        this.simbolo = simbolo;
        this.posicao = pos;
        this.vida = 50;
    }
    
    public Posicao getPosicao() {
        return posicao;
    }
    
    public int getVida() {
        return vida;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
