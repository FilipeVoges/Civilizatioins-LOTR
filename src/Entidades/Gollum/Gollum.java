/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Gollum;

import Entidades.Mapa.Posicao;
import java.util.ArrayList;

/**
 *
 * @author filipe
 */
public class Gollum {
    protected Anel anel;
    protected boolean visivel;
    protected boolean duelando;
    protected ArrayList<Charada> charadas;
    protected String simbolo = "¤";
    protected Posicao posicao;
    
    public Gollum(Posicao posicao){
        
        this.anel = new Anel();
        this.visivel = true;
        charadas = new ArrayList<>();
        this.posicao = posicao;
        criaCharadas();
    
    }
    
    
    
    public void criaCharadas(){
        
        /**************************************************/
        charadas.add(
            new Charada(
                //pergunta
                "Tem raízes misteriosas,\n" +
                "É mais alta que as frondosa\n" +
                "Sobe, sobe e também desce,\n" +
                "Mas não cresce nem descresce.",
                //resposta certa
                "Montanha",
                //respostas erradas
                "",
                ""
            )
        );
      
        /**************************************************/
        charadas.add(
            new Charada(
                "Trinta cavalos na colina encantada,\n" +
                "Primeiro cerceiam,\n" +
                "Depois pisoteiam,\n" +
                "Depois não fazem nada.",
                //resposta certa
                "Dentes",
                //respostas erradas
                "",
                ""
            )
        );
        
        /**************************************************/
        charadas.add(
            new Charada(
                "Sem asas volita, \n" +
                "Sem voz ele ulula,\n" +
                "Sem dentes mordica,\n" +
                "Sem boca murmura.",
                //resposta certa
                "Vento",
                //respostas erradas
                "",
                ""
            )
        );
        
        /**************************************************/
        charadas.add(
            new Charada(
                "Um olho no azul dum rosto\n" +
                "Viu outro olho no verde do outro.\n" +
                "\"Aquele olho é como este olho\"\n" +
                "Disse o primeiro olho,\n" +
                "\"Mas lá embaixo é seu lugar,\n" +
                "Aqui em cima é o meu lugar\".",
                //resposta certa
                "Sol das Margaridas",
                //respostas erradas
                "",
                ""
            )
        );
        
        /**************************************************/
        charadas.add(
            new Charada(
                "Não se pode ver, não se pode sentir,\n" +
                "Não se pode cheirar, não se pode ouvir.\n" +
                "Está sob as colinas e além das estrelas,\n" +
                "Cavidades vazias - ele vai enchê-las.\n" +
                "De tudo vem antes e vem em seguida,\n" +
                "Do riso é a morte, é o fim da vida.",
                //resposta certa
                "Escuridão",
                //respostas erradas
                "",
                ""
            )
        );

         /**************************************************/
        charadas.add(
            new Charada(
                "Caixinhas sem gonzos, tampas ou cadeado, \n" +
                "Lá dentro escondido um tesouro dourado.",
                //resposta certa
                "Ovos",
                //respostas erradas
                "",
                ""
            )
        );

         /**************************************************/
        charadas.add(
            new Charada(
                "Como a morte não tenho calor, \n" +
                "Vivo, mas sem respirar;\n" +
                "Sem sede, sempre a beber\n" +
                "Eucouraçado, sem tilintar.",
                //resposta certa
                "Peixe",
                //respostas erradas
                "",
                ""
            )
        );
        
        
         /**************************************************/
        charadas.add(
            new Charada(
                "Esta é a coisa que tudo devora\n" +
                "Feras, aves, plantas, flora.\n" +
                "Aço e ferro são sua comida,\n" +
                "E a dura pedra por ele moída;\n" +
                "Aos reis abate, a cidade arruína,\n" +
                "E a alta montanha faz pequenina.",
                //resposta certa
                "Tempo",
                //respostas erradas
                "",
                ""
            )
        );
        
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
        if(visivel) simbolo = "¤";
        else simbolo = "";
    }

    public boolean isDuelando() {
        return duelando;
    }

    public void setDuelando(boolean duelando) {
        this.duelando = duelando;
    }

    public Posicao getPosicao() {
        return posicao;
    }
    
    
    
    @Override
    public String toString(){
        return simbolo;
    }
    
}
