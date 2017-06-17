/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entidades.Construcao.Arquearia;
import Entidades.Construcao.Construcao;
import Entidades.Construcao.Estabulo;
import Entidades.Construcao.Principal;
import Entidades.Construcao.Quartel;
import Entidades.Gollum.Gollum;
import Entidades.Mapa.Mapa;
import Entidades.Mapa.Posicao;
import Entidades.Tropa.Arqueiro;
import Entidades.Tropa.Cavaleiro;
import Entidades.Tropa.Espadachim;
import Entidades.Tropa.Heroi;
import Entidades.Tropa.Tropa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author ASUS-DEV
 */
public class MapaView extends JPanel{
    
    private  JTable tableMapa;
    private  JScrollPane mapaContainer;
    private ArrayList<Posicao> posJogadores;
    
    
    
    public MapaView(Mapa mapa){
      
        //mapa
        iniciaPosicoes(mapa);
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
                if(row >= 0 && col >=0){
                    Object o = pegaValorPosicao(row, col);
                    cliqueNoMapa(o);
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });

        mapaContainer = new JScrollPane(tableMapa);
        this.add(mapaContainer, BorderLayout.CENTER);
        
	// table.setShowGrid(false);	//Mostra as linhas e colunas da tabela
    }
    
        /***************************************************************************/
    public void posicionaJogadores(int qtdeJogadores){
        for(int i = 0; i< qtdeJogadores; i++){
            
            //posicina edificio principal
            Posicao temp = posJogadores.get(i);
            setaValorPosicao(temp.getX(), temp.getY(), new Principal(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
            
            //posiciona Arquearia
            if(temp.getX() > 2) temp.setX(temp.getX() - 2);
            else temp.setX(temp.getX() + 2);     
            setaValorPosicao(temp.getX(), temp.getY(), new Arquearia(new Posicao(temp.getX(), temp.getY())));
            
            //posiciona Estabulo
            if(temp.getY() > 2) temp.setY(temp.getY() - 2);
            else temp.setY(temp.getY() + 2);     
            setaValorPosicao(temp.getX(), temp.getY(), new Estabulo(new Posicao(temp.getX(), temp.getY())));
            
            //posiciona Quartel
            if(temp.getX() > 4 )temp.setX(temp.getX() + 2);
            else temp.setX(temp.getX() - 2); 
            setaValorPosicao(temp.getX(), temp.getY(), new Quartel(new Posicao(temp.getX(), temp.getY())));
        }
    }
    
    
    /***************************************************************************/
    public Posicao posicaoLivreMaisProxima(Posicao atual){
        Posicao retorno = null;
        if(pegaValorPosicao(atual.getX() + 1, atual.getY() + 1) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY() + 1);
        }else if(pegaValorPosicao(atual.getX() + 1, atual.getY()) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY());
        }else if(pegaValorPosicao(atual.getX() + 1, atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY() - 1);
        }else if(pegaValorPosicao(atual.getX(), atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX(), atual.getY() - 1);
        }else if(pegaValorPosicao(atual.getX() - 1, atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY() - 1);
        }else if(pegaValorPosicao(atual.getX() - 1, atual.getY()) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY());
        }else if(pegaValorPosicao(atual.getX() - 1, atual.getY()+ 1) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY()+ 1);
        }else if(pegaValorPosicao(atual.getX(), atual.getY() + 1) == null){
            retorno =new Posicao(atual.getX(), atual.getY() + 1);
        }
        return retorno;
    }
    
    
      /***************************************************************************/
    public void cliqueNoMapa(Object o){

            //System.out.println("Posicao clicada " + row + " " + col);

        if(o != null){
            if(o.getClass().getSuperclass() == Construcao.class){
                 cliqueConstrucao(o);
            }
            else if(o.getClass().getSuperclass() == Tropa.class){
                 cliqueTropa(o);
            }
            else if(o.getClass() == Gollum.class){
                JOptionPane.showMessageDialog(null, "Meu precioso!!", "Gollum", 0);
            }                 
        }           
        
    }

    //clique em uma construcao
    /***************************************************************************/
    private void cliqueConstrucao(Object o){
        Construcao c = (Construcao) o;
        String[] options = new String[] {"Nova Tropa", "Reformar", "Cancelar"};
                    
        int response = JOptionPane.showOptionDialog(
            null, 
            "Vida: " + c.getVida()+ "\n Selecione o que você deseja fazer", 
            o.getClass().getSimpleName(),
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE,
            null, 
            options, 
            options[0]
        );
        
        switch(response){
            case 0://nova tropa
                Posicao posTropa = posicaoLivreMaisProxima(c.getPosicao());
              if(posTropa == null){
                  JOptionPane.showMessageDialog(null, "Não foi possível recrutar");
              }else if(o.getClass() == Arquearia.class){ 
                  setaValorPosicao(posTropa.getX(), posTropa.getY(), new Arqueiro());
              }else if(o.getClass() == Quartel.class){
                  setaValorPosicao(posTropa.getX(), posTropa.getY(), new Espadachim());
              }else if(o.getClass() == Estabulo.class){
                  setaValorPosicao(posTropa.getX(), posTropa.getY(), new Cavaleiro());
              }else if(o.getClass() == Principal.class){
                  setaValorPosicao(posTropa.getX(), posTropa.getY(), new Heroi());
              }
            break;
            
            case 1://reformar
            break;
                
            case 2://cancelar
            break;
        }
    }
    
    
    //clique em uma tropa
    /***************************************************************************/
    private void cliqueTropa(Object o){
        Tropa t = (Tropa)o;
        String[] options = new String[] {"Atacar", "Movimentar", "Cancelar"};
                    
        int response = JOptionPane.showOptionDialog(
            null, 
            "Vida: " + t.getVida()+ "\nSelecione o que você deseja fazer", 
            o.getClass().getSimpleName(),
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE,
            null, 
            options, 
            options[0]
        );
        
         switch(response){
            case 0://atacar
            break;
            
            case 1://movimentar
            break;
                
            case 2://cancelar
            break;
        }
    }
    
    
    public void iniciaPosicoes(Mapa mapa){
        posJogadores = new ArrayList<>();
        posJogadores.add(new Posicao(mapa.getTamX() -2 , 1));
        posJogadores.add(new Posicao(1, mapa.getTamY() -2));
        posJogadores.add(new Posicao(mapa.getTamX() -2, mapa.getTamY() -2));
        posJogadores.add(new Posicao(1, 1));
    }

    //Limpar Mapa
    public void limpar(int tamX, int tamY){
        for(int i = 0; i< tamX; i++){
            for(int j = 0; j< tamY; j++){
                tableMapa.setValueAt(null, i, j);
            }
        }
    }
    
     //Setter
    public void setaValorPosicao(int x, int y, Object item){
        tableMapa.setValueAt(item, x, y);
    }
    
    //Getter
    public Object pegaValorPosicao(int x, int y){
       return tableMapa.getValueAt(x, y);
    }
}
