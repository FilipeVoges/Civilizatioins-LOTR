/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Cidade.Cidade;
import Entidades.Gollum.Anel;
import Entidades.Mapa.Posicao;
import java.io.Serializable;

/**
 *
 * @author filipe
 */

public class Heroi extends Tropa implements Serializable{
    protected String nome;
    protected String simboloNormal = "✩";
    protected String simboloAnel = "✮";
    protected String nomeHeroi;//Ta duplicado? Atualizar Diagrama de classes quando definir o que é
    protected Anel anel;
    protected int acertosCharada;
    
    public Heroi(String nome, int velocidadeMovimento, int forca, int distanciaAtaque, int resistencia, Posicao posicao, Cidade cidade) {
        
        super(50, velocidadeMovimento, forca, distanciaAtaque, resistencia, "✩", posicao, cidade);
        this.anel = null;
        this.acertosCharada = 0;

    }
    
    public Anel perdeAnel() {
        this.anel = null;
        super.setSimbolo(simboloNormal);
        return new Anel();
    }

    public void pegaAnel(Anel anel) {
        this.anel = anel;
        super.setSimbolo(simboloAnel);
    }

    public int getAcertosCharada() {
        return acertosCharada;
    }

    public void setAcertosCharada(int acertosCharada) {
        this.acertosCharada = acertosCharada;
    }

    public String getNome() {
        return nome;
    }
    
}


