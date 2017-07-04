/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Gollum;

import java.io.Serializable;


public class Charada implements Serializable{
    protected String pergunta;
    protected String respostaCerta;
    protected String respostaErrada1;
    protected String respostaErrada2;

    public Charada(String pergunta, String respostaCerta, String respostaErrada1, String respostaErrada2) {
        this.pergunta = pergunta;
        this.respostaCerta = respostaCerta;
        this.respostaErrada1 = respostaErrada1;
        this.respostaErrada2 = respostaErrada2;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getRespostaCerta() {
        return respostaCerta;
    }

    public String getRespostaErrada1() {
        return respostaErrada1;
    }

    public String getRespostaErrada2() {
        return respostaErrada2;
    }
    
    
    
}
