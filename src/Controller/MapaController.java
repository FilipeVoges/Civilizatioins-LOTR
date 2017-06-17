/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Entity.Construcao.Arquearia;
import Model.Entity.Construcao.Construcao;
import Model.Entity.Construcao.Estabulo;
import Model.Entity.Construcao.Principal;
import Model.Entity.Construcao.Quartel;
import Model.Entity.Gollum.Gollum;
import Model.Entity.Mapa.Mapa;
import Model.Entity.Mapa.Posicao;
import Model.Entity.Tropa.Arqueiro;
import Model.Entity.Tropa.Cavaleiro;
import Model.Entity.Tropa.Espadachim;
import Model.Entity.Tropa.Heroi;
import Model.Entity.Tropa.Tropa;
import View.MapaView;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS-DEV
 */
public class MapaController {
 
    private MapaView mapaView;
    private Mapa mapa;
    private ArrayList<Posicao> posJogadores;
    
    public void iniciaPosicoes(Mapa mapa){
        this.mapa = mapa;
        posJogadores = new ArrayList<>();
        posJogadores.add(new Posicao(mapa.getTamX() -2 , 1));
        posJogadores.add(new Posicao(1, mapa.getTamY() -2));
        posJogadores.add(new Posicao(mapa.getTamX() -2, mapa.getTamY() -2));
        posJogadores.add(new Posicao(1, 1));
    }
     
    /***************************************************************************/
    public void iniciaJogo(int qtdeJogadores){
        for(int i = 0; i< qtdeJogadores; i++){
            
            //posicina edificio principal
            Posicao temp = posJogadores.get(i);
            mapaView.setaValorPosicao(temp.getX(), temp.getY(), new Principal(new Posicao(temp.getX(), temp.getY())));
            System.out.println(temp.getX() + " " + temp.getY());
            
            //posiciona Arquearia
            if(temp.getX() > 2) temp.setX(temp.getX() - 2);
            else temp.setX(temp.getX() + 2);     
            mapaView.setaValorPosicao(temp.getX(), temp.getY(), new Arquearia(new Posicao(temp.getX(), temp.getY())));
            
            //posiciona Estabulo
            if(temp.getY() > 2) temp.setY(temp.getY() - 2);
            else temp.setY(temp.getY() + 2);     
            mapaView.setaValorPosicao(temp.getX(), temp.getY(), new Estabulo(new Posicao(temp.getX(), temp.getY())));
            
            //posiciona Quartel
            if(temp.getX() > 4 )temp.setX(temp.getX() + 2);
            else temp.setX(temp.getX() - 2); 
            mapaView.setaValorPosicao(temp.getX(), temp.getY(), new Quartel(new Posicao(temp.getX(), temp.getY())));
        }
    }
    
 
    /***************************************************************************/
    public void cliqueNoMapa(int row, int col){
        
        if(row >= 0 && col >=0){
            //System.out.println("Posicao clicada " + row + " " + col);
            Object o = mapaView.pegaValorPosicao(row, col);
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
                  setValorPosicao(posTropa, new Arqueiro());
              }else if(o.getClass() == Quartel.class){
                  setValorPosicao(posTropa, new Espadachim());
              }else if(o.getClass() == Estabulo.class){
                  setValorPosicao(posTropa, new Cavaleiro());
              }else if(o.getClass() == Principal.class){
                  setValorPosicao(posTropa, new Heroi());
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
   
    
    /***************************************************************************/
    private Posicao posicaoLivreMaisProxima(Posicao atual){
        Posicao retorno = null;
        if(mapaView.pegaValorPosicao(atual.getX() + 1, atual.getY() + 1) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY() + 1);
        }else if(mapaView.pegaValorPosicao(atual.getX() + 1, atual.getY()) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY());
        }else if(mapaView.pegaValorPosicao(atual.getX() + 1, atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX() + 1, atual.getY() - 1);
        }else if(mapaView.pegaValorPosicao(atual.getX(), atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX(), atual.getY() - 1);
        }else if(mapaView.pegaValorPosicao(atual.getX() - 1, atual.getY() - 1) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY() - 1);
        }else if(mapaView.pegaValorPosicao(atual.getX() - 1, atual.getY()) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY());
        }else if(mapaView.pegaValorPosicao(atual.getX() - 1, atual.getY()+ 1) == null){
            retorno =new Posicao(atual.getX() - 1, atual.getY()+ 1);
        }else if(mapaView.pegaValorPosicao(atual.getX(), atual.getY() + 1) == null){
            retorno =new Posicao(atual.getX(), atual.getY() + 1);
        }
        return retorno;
    }
    
    
     //Setter
    /***************************************************************************/
    public void setValorPosicao(Posicao pos, Object item){
        mapaView.setaValorPosicao( pos.getX(), pos.getY(), item);
    }
    
    //Getter
    /***************************************************************************/
    public Object getValorPosicao(Posicao pos){
       return mapaView.pegaValorPosicao(pos.getX(), pos.getY());
    }
   
}
