/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Construcao;

import Entidades.Cidade.Cidade;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Arqueiro;

/**public Arqueiro recrutar(Posicao posDisponivel){
        return new Arqueiro(posDisponivel);
    }
 *
 * @author filipe
 */
public class Arquearia extends Construcao {
    private String simbolo = "◎";

    public Arquearia(Posicao pos, Cidade c) {
        //simbolo, posicao, tempoRecrutamento, recursoRecrutamento
        super("◎", pos, 1, 50, c);
    }
    
    public Arqueiro recrutar(Posicao posDisponivel){
        return new Arqueiro(posDisponivel, super.getCidade());
    }
    
}
