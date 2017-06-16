package civilization.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class Mapa extends JFrame {
	
    private int tamX;
    private int tamY;
	
    private static JTextArea textArea = new JTextArea("");
    private JButton criar = new JButton("Criar");
    private JButton reset = new JButton("Reset");
    private JButton pause = new JButton("Pause");
    private JPanel botoes = new JPanel();
    
    private JPanel mapaPanel = new JPanel();
    public static JTable mapa;
    private JScrollPane mapaContainer;

    public Mapa(int tamX, int tamY){
        this.tamX = tamX;
        this.tamY = tamY;
	
	mapaPanel.setLayout(new BorderLayout());
	botoes.setLayout(new FlowLayout());
	  
	//mapa
        mapa = new JTable(tamX,tamY);
	mapa.setBackground(Color.GREEN.darker().darker().darker().darker().darker());
	mapa.setForeground(Color.WHITE);
        mapa.setTableHeader(null);	//Esconde o cabeçalho da tabela
        mapaContainer = new JScrollPane(mapa);
        mapaPanel.add(mapaContainer, BorderLayout.CENTER);
        //table.setEnabled(false);	// Impede de selecionar e editar campos
        //table.setGridColor(Color.BLUE);// cor da grade da tabela
	// table.setShowGrid(false);	//Mostra as linhas e colunas da tabela

          
        
        this.add(mapaPanel, BorderLayout.NORTH);
        this.add(botoes, BorderLayout.CENTER);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        

    }//mapa
	
    public void limpaMapa(){
        for(int i = 0; i< tamX; i++){
            for(int j = 0; j< tamY; j++){
                mapa.setValueAt(null, i, j);
            }
        }
    }
    
}//class Mapa
