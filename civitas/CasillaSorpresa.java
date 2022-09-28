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
public class CasillaSorpresa extends Casilla {
    
    private MazoSorpresas mazo;
    private Sorpresa sorpresa;
    
    /**
     * Constructor para casillas de tipo SORPRESA
     * @param tipo define el tipo de casilla que se va a crear en este constructor.
     * @param nombre define el nombre de la casilla creada.
     * @param mazo define el mazo de sorpresas al que va a pertenecer esta casilla sorpresa.
     */
    CasillaSorpresa(String nombre, MazoSorpresas mazo){
        super(nombre);
        this.mazo = mazo;
    }
    
    @Override
    public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        sorpresa = mazo.siguiente();
        super.informe(iactual,todos);
        Diario.getInstance().ocurreEvento(toString());
        sorpresa.aplicarAJugador(iactual, todos);
        
    }
    
    @Override
    public String toString(){
        return "[Sorpresa: " + sorpresa.getTexto() + " ]";
    }
    
}
