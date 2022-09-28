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
public class MazoSorpresas {
    
    private ArrayList<Sorpresa> sorpresas;
    private boolean barajada;
    private int usadas;
    private boolean debug;
    
    /**
     * Inicializa todos los atributos por defecto a 0.
     */
    private void init(){
        sorpresas = new ArrayList();
        barajada = false;
        usadas = 0;
    }
    
    /**
     * Constructor por parámetros
     * @param debug define el modo debug.
     */
    MazoSorpresas(boolean debug){
        this.debug = debug;
        init();
        if(debug){
            Diario.getInstance().ocurreEvento("MODO DEBUG ACTIVADO.");
        }
    }
    
    /**
     * Constructor sin parámetros.
     */
    MazoSorpresas(){
        init();
        debug = false;
    }
    
    /**
     * Método que añade una sorpresa al mazo si este no ha sido barajado.
     * @param sorpresa define la sorpresa que queremos añadir.
     */
    void alMazo(Sorpresa sorpresa){
        if(!barajada){
            sorpresas.add(sorpresa);
        }
    }
    
    /**
     * Método para pasar a la siguiente sorpresa y almacenar la actual.
     * @return s: referencia a la carta sorrpesa que se tiene que aplicar al 
     * jugador que ha caido en esa casilla.
     */
    Sorpresa siguiente(){
        if(!barajada || usadas == sorpresas.size()){
            if(!debug){
                barajada = true;
                usadas = 0;
            } 
        }
        usadas++;
        Sorpresa s = sorpresas.get(0);
        sorpresas.add(s);
        sorpresas.remove(0);
        
        return s;
    }
    
        
     
    
}
