package View;

import Model.Entity.Construcao.Arquearia;
import Model.Entity.Construcao.Estabulo;
import Model.Entity.Construcao.Principal;
import Model.Entity.Construcao.Quartel;
import Model.Entity.Mapa.Posicao;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class TelaPrincipal extends JFrame {
	
    private ArrayList<Posicao> posJogadores;
    Mapa mapa;
      	
    

    public TelaPrincipal(int tamX, int tamY){
        
        //adiciona posição inicial dos jogadores
        posJogadores = new ArrayList<>();
        posJogadores.add(new Posicao(23, 1));
        posJogadores.add(new Posicao(1, 23));
        posJogadores.add(new Posicao(23, 23));
        posJogadores.add(new Posicao(1, 1));
        
        mapa = new Mapa(tamX, tamY);
        iniciaJogo(3);
        
        this.setLayout(new BorderLayout());
        this.add(mapa, BorderLayout.CENTER);
        this.pack();
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
    }
   
    
    public void iniciaJogo(int qtdeJogadores){
        for(int i = 0; i< qtdeJogadores; i++){
            
            //posicina edificio principal
            Posicao temp = posJogadores.get(i);
            mapa.setValorPosicao(temp, new Principal(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
            
            //posiciona Arquearia
            if(temp.getX() > 2) temp.setX(temp.getX() - 2);
            else temp.setX(temp.getX() + 2);     
            mapa.setValorPosicao(temp, new Arquearia(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
            
            //posiciona Estabulo
            if(temp.getY() > 2) temp.setY(temp.getY() - 2);
            else temp.setY(temp.getY() + 2);     
            mapa.setValorPosicao(temp, new Estabulo(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
            
            //posiciona Quartel
            if(temp.getX() > 4 )temp.setX(temp.getX() + 2);
            else temp.setX(temp.getX() - 2); 
            mapa.setValorPosicao(temp, new Quartel(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
        }
    }

   

}


        