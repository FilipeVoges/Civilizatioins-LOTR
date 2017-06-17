/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.MapaController;
import Model.Entity.Construcao.Construcao;
import Model.Entity.Gollum.Gollum;
import Model.Entity.Mapa.Mapa;
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
public class MapaView extends JPanel{
    
    private final JTable tableMapa;
    private final JScrollPane mapaContainer;
    private MapaController mapaController;
    
    
    //constructor
    public MapaView(Mapa mapa){
      
        //mapa
        tableMapa = new JTable(mapa.getTamX(),mapa.getTamX());
	tableMapa.setBackground(Color.GREEN.darker().darker());
	tableMapa.setForeground(Color.WHITE);
        tableMapa.setTableHeader(null);	//Esconde o cabeçalho da tabela
        tableMapa.setEnabled(false);	// Impede de selecionar e editar campos
        //mapa.setGridColor(Color.WHITE);// cor da grade da tabela
        
        tableMapa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableMapa.rowAtPoint(e.getPoint());
                int col = tableMapa.columnAtPoint(e.getPoint());
                mapaController.cliqueNoMapa(row, col);
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

        mapaContainer = new JScrollPane(tableMapa);
        this.add(mapaContainer, BorderLayout.CENTER);
        
	// table.setShowGrid(false);	//Mostra as linhas e colunas da tabela
    }
    
    //Setter
    public void setaValorPosicao(int x, int y, Object item){
        tableMapa.setValueAt(item, x, y);
    }
    
    //Getter
    public Object pegaValorPosicao(int x, int y){
       return tableMapa.getValueAt(x, y);
    }
    
    //Limpar Mapa
    public void limpar(int tamX, int tamY){
        for(int i = 0; i< tamX; i++){
            for(int j = 0; j< tamY; j++){
                tableMapa.setValueAt(null, i, j);
            }
        }
    }
}
