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
public class SorpresaConvertirme extends Sorpresa{
    
    SorpresaConvertirme(){
        super("Te conviertes en un Jugador Especulador");
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        super.informe(actual, todos);
        Jugador j = todos.get(actual);
        todos.add(actual, j.convertir());
    }
    
}
