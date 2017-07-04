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
 * @author ASUS-DEV
 */
public class Mago extends Tropa implements Serializable{
     
    public Mago(Posicao posicao, Cidade cidade){
        //vida, velocidadeMovimento, forca, distanciaAtaque, resistencia, simbolo, posicao, cidade
        super(50, 2, 100, 4, 5, "♕", posicao, cidade);
    }
   
    
}
