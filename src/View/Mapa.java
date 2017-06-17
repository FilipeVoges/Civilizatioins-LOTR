/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Entity.Construcao.Arquearia;
import Model.Entity.Construcao.Construcao;
import Model.Entity.Construcao.Estabulo;
import Model.Entity.Construcao.Principal;
import Model.Entity.Construcao.Quartel;
import Model.Entity.Gollum.Gollum;
import Model.Entity.Mapa.Posicao;
import Model.Entity.Tropa.Arqueiro;
import Model.Entity.Tropa.Cavaleiro;
import Model.Entity.Tropa.Espadachim;
import Model.Entity.Tropa.Heroi;
import Model.Entity.Tropa.Tropa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ASUS-DEV
 */
public class Mapa extends JPanel{
    private int tamX;
    private int tamY;
    
    private JTable mapa;
    private JScrollPane mapaContainer;
    
    
    //constructor
    public Mapa(int tX, int tY){
         
        this.tamX = tamX;
        this.tamY = tamY;
        
        //mapa
        mapa = new JTable(tX,tY);
	mapa.setBackground(Color.GREEN.darker().darker());
	mapa.setForeground(Color.WHITE);
        mapa.setTableHeader(null);	//Esconde o cabeçalho da tabela
        mapa.setEnabled(false);	// Impede de selecionar e editar campos
        //mapa.setGridColor(Color.WHITE);// cor da grade da tabela
        
        mapa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cliqueNoMapa(e);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        mapaContainer = new JScrollPane(mapa);
        this.add(mapaContainer, BorderLayout.CENTER);
        
	// table.setShowGrid(false);	//Mostra as linhas e colunas da tabela
    }
     
    //Setter
    public void setValorPosicao(Posicao pos, Object item){
        mapa.setValueAt(item, pos.getX(), pos.getY());
    }
    
    //Getter
    public Object getValorPosicao(Posicao pos){
       return mapa.getValueAt(pos.getX(), pos.getY());
    }
    
   // listener de clique no mapa
    void cliqueNoMapa(MouseEvent e){
        int row = mapa.rowAtPoint(e.getPoint());
        int col = mapa.columnAtPoint(e.getPoint());
        if(row >= 0 && col >=0){
            System.out.println("Posicao clicada " + row + " " + col);
            Object o = mapa.getValueAt(row, col);
            if(o!=null){
                if(o.getClass() == Arquearia.class) JOptionPane.showMessageDialog(null, "Arquearia");
                else if(o.getClass() == Principal.class) JOptionPane.showMessageDialog(null, "Principal");
                else if(o.getClass() == Estabulo.class) JOptionPane.showMessageDialog(null, "Estabulo");
                else if(o.getClass() == Quartel.class) JOptionPane.showMessageDialog(null, "Quartel");
                else if(o.getClass() == Arqueiro.class) JOptionPane.showMessageDialog(null, "Arqueiro");
                else if(o.getClass() == Cavaleiro.class) JOptionPane.showMessageDialog(null, "Cavaleiro");
                else if(o.getClass() == Espadachim.class) JOptionPane.showMessageDialog(null, "Espadachim");
                else if(o.getClass() == Heroi.class) JOptionPane.showMessageDialog(null, "Heroi");
                else if(o.getClass() == Gollum.class) JOptionPane.showMessageDialog(null, "Gollum");
                
            }
               
        }
    }
    
    //Limpar Mapa
    public void limpar(){
        for(int i = 0; i< tamX; i++){
            for(int j = 0; j< tamY; j++){
                mapa.setValueAt(null, i, j);
            }
        }
    }
    
    
}
