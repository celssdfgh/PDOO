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
public class CasillaDescanso extends Casilla{
    
    CasillaDescanso(String nombre){
        super(nombre);
    }
    
    public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        informe(iactual,todos);
    }
    
    @Override
    public String toString(){
        return "[ " + super.getNombre() + " ]";
    }
    
}
