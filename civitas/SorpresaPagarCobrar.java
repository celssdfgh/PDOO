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
public class SorpresaPagarCobrar extends Sorpresa {
    
    private int valor;
    
    SorpresaPagarCobrar(String texto, int valor){
        super(texto);
        this.valor=valor;
    }
    
    /**
     * Método para aplicar la sorpresa PAGARCOBRAR a un jugador.
     * @param actual define el jugador actual.
     * @param todos define la lista de jugadores actuales.
     */
    @Override
    public void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        super.informe(actual, todos);
        todos.get(actual).modificaSaldo(valor);
    }
    
    @Override
    public String toString(){
        String t;
        t = "Sorpresa: " + super.getTexto();
        return t;
    }
    
}
