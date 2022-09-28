/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import java.util.ArrayList;

/**
 * CasillaCalle 
 * @author celiaa
 */
public class CasillaCalle extends Casilla {
    
    private float precioCompra, precioEdificar, precioBaseAlquiler;
    private int numCasas, numHoteles;
    
    static private float FACTORALQUILERCALLE = 1.0f;
    static private float FACTORALQUILERCASA = 1.0f;
    static private float FACTORALQUILERHOTEL = 4.0f;
    
    private Jugador propietario;
    
    /**
     * Constructor para casillas de tipo CALLE.
     * @param casilla define el nombre de la casilla creada.
     * @param precioCompra define el precio de comprar esta casilla.
     * @param precioEdificar define el precio de edificar en esta casilla.
     * @param precioBaseAlquiler define el precio a pagar al caer en esta casilla.
     */
    CasillaCalle(String nombre, float precioCompra, float precioEdificar, float precioBaseAlquiler){
        super(nombre);
        this.precioCompra = precioCompra;
        this.precioEdificar = precioEdificar;
        this.precioBaseAlquiler = precioBaseAlquiler;
        propietario = null;
        numCasas = 0;
        numHoteles = 0;
    }
    
    public Jugador getPropietario(){
        return propietario;
    }
    
    /**
     * Método consultor del atributo precioCompra.
     * @return precioCompra: precio de comprar una casilla.
     */
    public float getPrecioCompra(){
        return precioCompra;
    }
    
    /**
     * Método consultor del atributo precioEdificar.
     * @return precioEdificar: precio de edificar en una casilla.
     */
    public float getPrecioEdificar(){
        return precioEdificar;
    }
    
    //No esta este metodo en el diagrama
    float getPrecioBaseAlquiler(){
        return precioBaseAlquiler;
    }
    
    /**
     * Método consultor del atributo numCasas.
     * @return numCasas: número de casas en una casilla.
     */
    public int getNumCasas(){
        return numCasas;
    }
    
    /**
     * Método consultor del atributo numHoteles.
     * @return numHoteles: número de hoteles en una casilla.
     */
    public int getNumHoteles(){
        return numHoteles;
    }
    
    /**
     * Metodo que devuelve el precio a pagar al caer en una casilla.
     * @return precio que depende del nº de hoteles y casas que haya en esa casilla.
     */
    float getPrecioAlquilerCompleto(){
        return precioBaseAlquiler*FACTORALQUILERCALLE*(1+(numCasas*FACTORALQUILERCASA)+numHoteles*FACTORALQUILERHOTEL*4);
    }
    
    /**
     * Método que devuelve el total de casas y hoteles en una casilla.
     * @return suma de casas y hoteles de la casilla.
     */
    public int cantidadCasasHoteles(){
        return numCasas+numHoteles;
    }
    
    /**
     * 
     * @param jugador
     * @return 
     */
    boolean construirCasa(Jugador jugador){
        boolean result = false;
        if(esEsteElPropietario(jugador) && (numCasas < 4)){
            result = true;
            jugador.paga(precioEdificar);
            numCasas++;
        }
        return result;
    }
    
    /**
     * 
     * @param jugador
     * @return 
     */
    boolean construirHotel(Jugador jugador){
        boolean result = false;
        if(esEsteElPropietario(jugador) && (cantidadCasasHoteles()<8) && (numHoteles < 4 && numCasas >= 4)){
            result = true;
            derruirCasas(4, jugador);
            jugador.paga(precioEdificar);
            numHoteles++;
        }else{
            result = false;
        }
        return result;
    }
    
    /**
     * Método que decrementa el contador de casa construidas en una casilla.
     * @param numero define el numero de casas que se quieren derruir.
     * @param jugador define el jugador actual.
     * @return hecho: indica si la operacion se ha realizado (true) o no (false).
     */
    boolean derruirCasas(int numero, Jugador jugador){
        boolean hecho;
        if(esEsteElPropietario(jugador) && numCasas >= numero){
            numCasas = numCasas-numero;
            hecho = true;
        }else{
            hecho = false;
        }
        return hecho;
    }
    
    /**
     * Método que comprueba que el jugador pasado como parámetro es el propietario de una casilla.
     * @param jugador define el jugador que queremos comprobar.
     * @return true: el jugador es el propietario.
     */
    public boolean esEsteElPropietario(Jugador jugador){
        return propietario == jugador;
    }
    
    /**
     * Método que comprueba si una casilla tiene propietario.
     * @return true: la casilla actual tiene propietario.
     */
    public boolean tienePropietario(){
        boolean tiene = false;
        if(propietario != null){
            tiene = true;
        }
        return tiene;
    }
    
    /**
     * Método que hace que un jugador pague el alquiler de una casilla.
     * @param jugador define el jugador que debe pagar el alquiler.
     */
    public void tramitarAlquiler(Jugador jugador){
        if(tienePropietario() && !esEsteElPropietario(jugador)){
            jugador.pagaAlquiler(getPrecioAlquilerCompleto());
            propietario.recibe(getPrecioAlquilerCompleto());
        }
    }
    
    void actualizaPropietarioPorConversion(Jugador jugador){
        propietario = jugador;
    }
    
    boolean comprar(Jugador jugador){
        boolean result;
        if(tienePropietario()){
            result = false;
        }else{
                propietario = jugador;
                propietario.paga(this.getPrecioCompra());
                result = true;
             
        }
        return result;
    }
     
    @Override
    public void recibeJugador(int iactual, ArrayList<Jugador> todos){
        super.informe(iactual,todos);
        Diario.getInstance().ocurreEvento(toString());
        Jugador jugador = todos.get(iactual);
        
        if(!tienePropietario()){
            jugador.puedeComprarCasilla();
        }else{
            tramitarAlquiler(jugador);
        }
    }
   
    
    @Override
    public String toString(){
        String salida;
     
        salida = "[ " + super.getNombre() + "--> Propietario: " + propietario + " || Precio Compra: " + precioCompra + 
                " || Precio edificar: " + precioEdificar + " || Alquiler base: "
                + precioBaseAlquiler + " || CASAS: " + numCasas + " || HOTELES: " + numCasas + " ] \n"; 
        return salida;
    }
    
    
}
