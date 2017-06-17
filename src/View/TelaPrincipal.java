package View;


import Entidades.Mapa.Mapa;
import Entidades.Mapa.Posicao;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TelaPrincipal extends JFrame {
	

    private static MapaView mapaView;
    private static InfoView infoView;
    
      	
    public TelaPrincipal(Mapa mapa){
        
        this.setLayout(new BorderLayout());
        
       
        mapaView = new MapaView(mapa);
        this.add(mapaView, BorderLayout.NORTH);
        
        infoView = new InfoView();
        this.add(infoView, BorderLayout.SOUTH);
        
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
    }
    
    public static MapaView getMapaView() {
        return mapaView;
    }
}
      