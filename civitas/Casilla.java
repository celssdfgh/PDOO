/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *Clase Casilla
 * @author celiaa 
 */
public class Casilla {
    
    private String nombre;
    
    /**
     * Constructor para casillas de tipo DESCANSO.
     * @param tipo define el tipo de casilla que se va a crear en este constructor. 
     * @param nombre define el nombre de la casilla creada.
     */
    Casilla(String nombre){
        //init();
        this.nombre = nombre;
    }
    
    
    //No esta en el diagrama
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Método que informa al Diario sobre el jugador que ha caido en la casilla actual.
     * @param actual define el jugador actual.
     * @param todos define la lista de jugadores actuales.
     */
    void informe(int actual, ArrayList<Jugador>todos){
        Diario.getInstance().ocurreEvento(todos.get(actual).getNombre() + " ha caido en la casilla " +this.getNombre() + ".");
    }
    
    /**
     * 
     * @param iactual
     * @param todos 
     */
    public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        informe(iactual,todos);
    }
    
    @Override
    public String toString(){
        String salida;
     
        salida = "[ " + nombre + "] \n"; 
        return salida;
    }
    
}
