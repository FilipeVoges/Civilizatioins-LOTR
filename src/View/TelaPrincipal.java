package View;

import Controller.MapaController;
import Model.Entity.Construcao.Arquearia;
import Model.Entity.Construcao.Estabulo;
import Model.Entity.Construcao.Principal;
import Model.Entity.Construcao.Quartel;
import Model.Entity.Mapa.Mapa;
import Model.Entity.Mapa.Posicao;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class TelaPrincipal extends JFrame {
	

    MapaView mapaView;
    MapaController mapaController;
      	
    public TelaPrincipal(Mapa mapa){
        
        mapaController = new MapaController();
        mapaController.iniciaPosicoes(mapa);
        mapaView = new MapaView(mapa);
        
        this.setLayout(new BorderLayout());
        this.add(mapaView, BorderLayout.CENTER);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
    }
}
      