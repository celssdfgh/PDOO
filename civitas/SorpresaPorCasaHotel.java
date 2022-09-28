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
public class SorpresaPorCasaHotel extends Sorpresa {
    
    private int valor;
    
    SorpresaPorCasaHotel(String texto, int valor){
        super(texto);
        this.valor=valor;
    }
    
    /**
     * Método para aplicar la sorpresa PORCASAHOTEL a un jugador.
     * @param actual define el jugador actual.
     * @param todos define la lista de jugadores actuales.
     */
    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        //todos.get(actual).tieneAlgoQueGestionar()
        if(todos.get(actual).cantidadCasasHoteles()<=0){
             Diario.getInstance().ocurreEvento("No se puede aplicar la sorpresa al jugador porque aun no tiene casas ni hoteles");
        }else{
            super.informe(actual, todos);
            int nCasasHoteles = todos.get(actual).cantidadCasasHoteles();
            Diario.getInstance().ocurreEvento("Numero total de casa y hoteles: " + nCasasHoteles);
            todos.get(actual).modificaSaldo(valor*nCasasHoteles);
            
        }     
    }
    
    @Override
    public String toString(){
        String t;
        t = "Sorpresa: " + super.getTexto();
        return t;
    }
    
}
