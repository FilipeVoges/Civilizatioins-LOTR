/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Tropa;

/**
 *
 * @author filipe
 */
public class Heroi extends Tropa{
    private String simboloNormal = "✩";
    private String simboloAnel = "✮";
    
    private enum tipoHeroi{
        NORMAL, ULTRA;
    }
    
    public Heroi(){
        super(25, 1, 10, 1,"✩");
    }
}


