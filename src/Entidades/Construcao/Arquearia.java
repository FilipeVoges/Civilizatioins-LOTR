/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Arqueiro;
import java.io.Serializable;

/**
 *
 * @author filipe
 */
public class Arquearia extends Construcao implements Serializable{
    protected String simbolo = "◎";

    public Arquearia(Posicao pos, Cidade c) {
        //simbolo, posicao, recursoRecrutamento, vida, cidade
        super("◎", pos, 50, 100, c);
    }
    
    public Arqueiro recrutar(Posicao posDisponivel){
        return new Arqueiro(posDisponivel, super.getCidade());
    }
    
}
