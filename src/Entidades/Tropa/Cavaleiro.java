/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Cavaleiro extends Tropa implements Serializable{
    
    private String simbolo = "♘";
    public Cavaleiro(Posicao posicao, Cidade cidade){
        //vida, velocidadeMovimento, forca, distanciaAtaque, resistencia, simbolo, posicao, cidade
        super(20, 3, 15, 1, 3, "♘", posicao, cidade);
    }   
}
