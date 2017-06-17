/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author ASUS-DEV
 */
public class InfoView extends JPanel{
    
    MapaView mapaView;
    private JButton btnIniciaJogo, btnConectaPartidaExistente, btnPassaVez, btnDesistir;
    
    public InfoView(){
        mapaView = TelaPrincipal.getMapaView();
        btnIniciaJogo = new JButton("Iniciar Jogo");
        btnConectaPartidaExistente = new JButton("Conectar a partida");
        btnPassaVez = new JButton("Passa a Vez");
        btnDesistir = new JButton("Desistir");
        this.setLayout(new FlowLayout());
        this.add(btnIniciaJogo);
        this.add(btnConectaPartidaExistente);
        this.add(btnPassaVez);
        this.add(btnDesistir);
        btnPassaVez.setVisible(false);
        btnDesistir.setVisible(false);
        
        btnIniciaJogo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                btnIniciaJogo.setVisible(false);
                btnConectaPartidaExistente.setVisible(false);
                btnPassaVez.setVisible(true);
                btnDesistir.setVisible(true);
                
                mapaView.posicionaJogadores(4);
            }

            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
    }
}
