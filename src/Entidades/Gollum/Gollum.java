/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Gollum;

import java.util.ArrayList;

/**
 *
 * @author filipe
 */
public class Gollum {
    private Anel anel;
    private boolean visivel;
    private boolean duelando;
    private ArrayList<String> charadas;
    private ArrayList<ArrayList> respostas;
    private String simbolo = "¤";
    
    public Gollum(){
        this.anel = new Anel();
        this.visivel = true;
        charadas = new ArrayList<>();
        respostas = new ArrayList<>();
        criaCharadas();
    
    }
    
    
    
    public void criaCharadas(){
        ArrayList<String> listaRespostas;
        
        /**************************************************/
        charadas.add(
                "Tem raízes misteriosas,\n" +
                "É mais alta que as frondosa\n" +
                "Sobe, sobe e também desce,\n" +
                "Mas não cresce nem descresce.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Montanha");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Trinta cavalos na colina encantada,\n" +
            "Primeiro cerceiam,\n" +
            "Depois pisoteiam,\n" +
            "Depois não fazem nada.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Dentes");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Sem asas volita, \n" +
            "Sem voz ele ulula,\n" +
            "Sem dentes mordica,\n" +
            "Sem boca murmura.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Vento");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Um olho no azul dum rosto\n" +
            "Viu outro olho no verde do outro.\n" +
            "\"Aquele olho é como este olho\"\n" +
            "Disse o primeiro olho,\n" +
            "\"Mas lá embaixo é seu lugar,\n" +
            "Aqui em cima é o meu lugar\".");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Sol das Margaridas");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Não se pode ver, não se pode sentir,\n" +
            "Não se pode cheirar, não se pode ouvir.\n" +
            "Está sob as colinas e além das estrelas,\n" +
            "Cavidades vazias - ele vai enchê-las.\n" +
            "De tudo vem antes e vem em seguida,\n" +
            "Do riso é a morte, é o fim da vida.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Escuridão");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Caixinhas sem gonzos, tampas ou cadeado, \n" +
            "Lá dentro escondido um tesouro dourado.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Ovos");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Como a morte não tenho calor, \n" +
            "Vivo, mas sem respirar;\n" +
            "Sem sede, sempre a beber\n" +
            "Eucouraçado, sem tilintar.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Peixe");
        respostas.add(listaRespostas);
        
        
        /**************************************************/
        charadas.add(
            "Esta é a coisa que tudo devora\n" +
            "Feras, aves, plantas, flora.\n" +
            "Aço e ferro são sua comida,\n" +
            "E a dura pedra por ele moída;\n" +
            "Aos reis abate, a cidade arruína,\n" +
            "E a alta montanha faz pequenina.");
        
        listaRespostas = new ArrayList<>();
        listaRespostas.add("Tempo");
        respostas.add(listaRespostas);
    }
    
     public Anel perdeAnel() {
        this.anel = null;
        return new Anel();
    }

    public void pegaAnel(Anel anel) {
        this.anel = anel;
    }
    
    public boolean isVisivel() {
        return visivel;
    }

    public void setVisivel(boolean visivel) {
        this.visivel = visivel;
    }

    public boolean isDuelando() {
        return duelando;
    }

    public void setDuelando(boolean duelando) {
        this.duelando = duelando;
    }
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
