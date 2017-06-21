/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Tropa;

import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Cavaleiro extends Tropa{
    
    private String simbolo = "♘";
    public Cavaleiro(Posicao posicao) {
        super(20, 3, 5, 1, 3, "♘", posicao);
    }   
}
