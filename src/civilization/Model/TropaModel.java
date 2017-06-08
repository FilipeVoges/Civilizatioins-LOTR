/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civilization.Model;

import civilization.Model.Entity.Mapa.Posicao;
import civilization.Model.Entity.Tropa.Tropa;

/**
 *
 * @author filipe
 */
public class TropaModel {
    
    private Tropa tropa;

    public TropaModel(Tropa tropa) {
        this.tropa = tropa;
    }

    public Tropa getTropa() {
        return tropa;
    }

    public void setTropa(Tropa tropa) {
        this.tropa = tropa;
    }
    
    private int calculaDistancia(Tropa tropaAliada, Posicao destino){
        Posicao posicaoAtual = tropaAliada.getPosicao();
        
        return 0; 
    }
    
    
}
