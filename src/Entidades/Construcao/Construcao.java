/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Construcao {
    
    private Posicao posicao;
    private int vida;
    private final String simbolo;
    private int vidaMaxima;
    private boolean destruido;
    private int recursoRecrutamento;
    private Cidade cidade;

    public Construcao() {
        this.simbolo = null;
    }
    
    
    public Construcao(
            String simbolo, 
            Posicao pos, 
            int tempoRecrutamento, 
            int recursoRecrutamento, 
            Cidade cidade
    ){
        this.simbolo = simbolo;
        this.posicao = pos;
        this.recursoRecrutamento = recursoRecrutamento;
        this.vida = 50;
        this.vidaMaxima = 50;
        this.destruido = false;
        this.cidade = cidade;
    }
    
    
    //Getters and Setters
    public Posicao getPosicao() {
        return posicao;
    }
    
    public int getVida() {
        return vida;
    }

    public boolean isDestruido() {
        return destruido;
    }

    public void setDestruido(boolean destruido) {
        this.destruido = destruido;
    }

    public int getRecursoRecrutamento() {
        return recursoRecrutamento;
    }

    public Cidade getCidade() {
        return cidade;
    }
    
    // outras funções
    public int calculaReforma(){
       return (this.vidaMaxima - this.vida) * 10;
    }
    
    public void reformar(){
        this.vida = this.vidaMaxima;
    }
    
  
    @Override
    public String toString(){
        return simbolo;
    }
    
    
    
    
    
    
}
