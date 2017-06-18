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
    
    private int tamX;
    private int tamY;
    ArrayList<Jogador> jogadores;
    ArrayList<Cidade> cidades;
    Gollum gollum;
    
    
    public Mapa(int tX, int tY){
        this.tamX = tX;
        this.tamY = tY;
        jogadores = new ArrayList<>();
        cidades = new ArrayList<>();
        gollum = new Gollum();
    }

    public int getTamX() {
        return tamX;
    }

    public int getTamY() {
        return tamY;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> cidades) {
        this.cidades = cidades;
    }
    
    
    
    
    
}
