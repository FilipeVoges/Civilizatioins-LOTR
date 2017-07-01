/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Mapa;

import Entidades.Cidade.Cidade;
import Entidades.Gollum.Gollum;
import Entidades.Jogador.Jogador;
import java.util.ArrayList;

/**
 *
 * @author filipe
 */

public class Mapa {
    
    protected int tamX;
    protected int tamY;
    protected ArrayList<Cidade> cidades;
    protected Gollum gollum;
    
    
    public Mapa(int tX, int tY){
        this.tamX = tX;
        this.tamY = tY;
        cidades = new ArrayList<>();
        gollum = new Gollum();
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    
    
    
    
}
