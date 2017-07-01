/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entidades.Jogador.Jogador;
import Entidades.Mapa.Mapa;
import View.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author filipe
 */
public class Main {
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(() -> {
            JFrame tela = new TelaPrincipal(new Mapa(25, 25));
            tela.setVisible(true);
        });    

    } 
}
