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
public class Principal extends Construcao{
    private String Simbolo = "⌂";

    public Principal(Posicao pos) {
        super("⌂", pos, 1, 50);
    }
}
