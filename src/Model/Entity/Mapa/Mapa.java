/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entity.Mapa;

/**
 *
 * @author filipe
 */
public class Mapa {
    
    private int tamX;
    private int tamY;
    
    
    public Mapa(int tX, int tY){
        this.tamX = tX;
        this.tamY = tY;
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }
    
    
    
}
