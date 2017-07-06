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
    protected String simbolo;
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
    
    public void recebeDano(int dano) {
        vida -= dano;
        if(vida <= 0){
            vida = 0;
            destruido = true;
            simbolo = "▩";
        }
    }

    public boolean isDestruido() {
        return destruido;
    }

    public int getRecursoRecrutamento() {
        return recursoRecrutamento;
    }

    public Cidade getCidade() {
        return cidade;
    }
    
    
    public int calculaReforma(){
       return (this.vidaMaxima - this.vida) * 10;
    }
    
    public boolean reformar(){
        if(!destruido){
            this.vida = this.vidaMaxima;
            return true;
        }else return false;
    }
    
  
    @Override
    public String toString(){
        return simbolo;
    }
    
    
    
    
    
    
}
