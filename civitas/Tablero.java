/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author celiaa
 */
public class Tablero {
    
    private ArrayList<Casilla> tablero;
    private boolean porSalida;
    
    /**
     * Constructor para crear un tablero.
     * Crea una casilla de salida.
     */
    Tablero(){
        tablero = new ArrayList<>();
        Casilla salida = new Casilla("Salida");
        this.tablero.add(salida);       
        porSalida = false;              
    }
    
    /**
     * Método que devuelve true si el parametro es una indice de una casilla valido.
     * @param numCasilla define el índice que queremos comprobar.
     * @return true: el indice es valido.
     */
    private boolean correcto(int numCasilla){
        return numCasilla >= 0 && numCasilla < tablero.size();
    }
    
    /**
     * Método que indica si se ha pasado por salida y cambia porSalida a false.
     * @return true: se ha pasado por salida.
     */
    boolean computarPasoPorSalida(){
        boolean r = porSalida;
        porSalida = false;
        return r;
    }
    
    /**
     * Método que añade una casilla al tablero.
     * @param casilla define la casilla que queremos añadir al tablero.
     */
    void añadeCasilla(Casilla casilla){
        tablero.add(casilla);
    }
    
    /**
     * Método consultor de una casilla.
     * @param numCasilla define el indice de la casilla que queremos consultar.
     * @return 
     */
    public Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla)){
            return tablero.get(numCasilla);
        }else{
            return null;
        }
    }
    
    /**
     * Método que calcula la nueva posición en el tablero.
     * @param actual define la posición en la que se encuentra actualmente.
     * @param tirada número de casillas que se avanza en el tablero.
     * @return pos: nueva posición calculada.
     */
    int nuevaPosicion(int actual, int tirada){
        int pos;
        pos = tirada + actual;
        
        if(pos >= tablero.size()){
            pos = pos % tablero.size();
            porSalida = true;
        }
        return pos;
    }
    
    
    public ArrayList<Casilla> getCasillas(){
        return tablero;
    }
    
}
