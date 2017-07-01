/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Construcao implements Serializable {
    
    protected Posicao posicao;
    protected int vida;
    protected final String simbolo;
    protected int vidaMaxima;
    protected boolean destruido;
    protected int recursoRecrutamento;
    protected Cidade cidade;

    public Construcao() {
        this.simbolo = null;
    }
    
    
    public Construcao(
            String simbolo, 
            Posicao pos, 
            int recursoRecrutamento,
            int vida,
            Cidade cidade
    ){
        this.simbolo = simbolo;
        this.posicao = pos;
        this.recursoRecrutamento = recursoRecrutamento;
        this.vida = vida;
        this.vidaMaxima = vida;
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
