/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Mapa.Posicao;

/**
 *
 * @author filipe
 */
public class Estabulo extends Construcao{
    private String simbolo = "♞";
    
    public Estabulo(Posicao pos){
        super("♞", pos, 2, 70);
    }
}
