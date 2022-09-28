/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;


import java.util.ArrayList;

/**
 *Clase Sorpresa
 * @author celiaa
 */
public abstract class Sorpresa {
    private String texto;
    private int valor;
    private MazoSorpresas mazo; //No lo uso comprobar
    
    /**
     * Constructor por parámetros.
     * @param tipo define el tipo de sorpresa.
     * @param texto define el enunciado de la sorpresa.
     * @param valor define el valor de la sorpresa.
     */
    Sorpresa(String texto){
        this.texto = texto;
        mazo = new MazoSorpresas();
    }
    
    abstract void aplicarAJugador(int actual, ArrayList<Jugador> todos);
    
    /**
     * Método que informa al Diario que se esta aplicando una sorpresa al jugador.
     * @param actual 
     * @param todos define el jugador al que se le aplica la sorpresa.
     */
    /*public void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento(toString());
        if(todos.get(actual).cantidadCasasHoteles()>0 && tipo == TipoSorpresa.PORCASAHOTEL){
            Diario.getInstance().ocurreEvento("Se esta aplicando la sorpresa " + tipo + " al jugador " + todos.get(actual).getNombre());
        }else if(todos.get(actual).cantidadCasasHoteles()<=0 && tipo == TipoSorpresa.PORCASAHOTEL){
            Diario.getInstance().ocurreEvento("No se puede aplicar la sorpresa " + tipo + " al jugador porque aun no tiene casas ni hoteles");
        }else{
            Diario.getInstance().ocurreEvento("Se esta aplicando la sorpresa " + tipo + " al jugador " + todos.get(actual).getNombre());
        }
    }
    */
    
    public void informe(int actual, ArrayList<Jugador> todos){
        Diario.getInstance().ocurreEvento("Se esta aplicando la sorpresa al jugador " + todos.get(actual).getNombre());
    }
    
    public String getTexto(){
        return texto;
    }
    
    @Override
    public String toString(){
        String t;
        t = "Sorpresa: " + texto;
        return t;
    }
    
}
