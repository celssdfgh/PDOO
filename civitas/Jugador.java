/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;


import static java.lang.Float.compare;
import java.util.ArrayList;

/**
 *Clase Jugador
 * @author celiaa
 */
public class Jugador implements Comparable<Jugador> {
    
    protected static int CasasMax=4;
    protected static int CasasPorHotel=4;
    protected static int HotelesMax=4;
    protected static float PasoPorSalida=1000;
    private static float SaldoInicial=7500;
    private int casillaActual;
    private String nombre;
    private boolean puedeComprar;
    private float saldo;
    private ArrayList<CasillaCalle> propiedades = new ArrayList();
    private boolean esEspeculador;
    
    /**
     * Constructor para crear un jugador.
     * @param nombre define el nombre asignado al jugador.
     */
    Jugador (String nombre){
        this.nombre = nombre;
        casillaActual = 0;
        puedeComprar = true;
        saldo = SaldoInicial;
        esEspeculador = false;
    }
    
    /**
     * Constructor de copia
     * @param otro define el otro jugador del que vamos a reasignar los atributos.
     */
    protected Jugador (Jugador otro){
        this.nombre = otro.nombre;
        this.casillaActual = otro.casillaActual;
        this.puedeComprar = otro.puedeComprar;
        this.saldo = otro.saldo;
    }
    
    int cantidadCasasHoteles(){
        int p = 0;
        for(int i=0; i<propiedades.size(); i++){
            p = p + propiedades.get(i).getNumCasas()+propiedades.get(i).getNumHoteles();
        }
        return p;
    }
    
    /**
     * Método para comparar el saldo de dos jugadores.
     * @param otro define el otro jugador con el que se va a comparar
     * @return true
     */
    @Override
    public int compareTo(Jugador otro) {
        return compare(saldo, otro.saldo);
    }
    
    boolean comprar(CasillaCalle titulo){
        boolean result = false;
        
        if(puedeComprar){
            float precio = titulo.getPrecioCompra();
            if(puedoGastar(precio)){
                result = titulo.comprar(this);
                titulo.esEsteElPropietario(this);
                //paga(precio);
                propiedades.add(titulo);
                Diario.getInstance().ocurreEvento(nombre + " compra la propiedad " + titulo.getNombre() + " por " + titulo.getPrecioCompra() + "€");
                puedeComprar = false;
            }else{
                Diario.getInstance().ocurreEvento(nombre + " no tiene saldo para comprar la propiedad " + titulo.getNombre());
            }
        }
        return result;
    }
    
    boolean construirCasa(int ip){
        boolean result;
        result = false;
        boolean existe = existeLaPropiedad(ip);
        if(existe){
            CasillaCalle propiedad = propiedades.get(ip);
            boolean puedoEdificar = puedoEdificarCasa(propiedad);
            float precioEdificar = propiedad.getPrecioEdificar();
            if(puedoGastar(precioEdificar) && propiedad.getNumCasas()<getCasasMax()){
                puedoEdificar = true;
            }else{
                System.out.println("\nERROR: No tienes saldo o has superado el número máximo de casas.\n");
            }
            if(existe && puedoEdificar){
                propiedad.construirCasa(this);
                //numCasas++;
                result = true;
                Diario.getInstance().ocurreEvento(nombre + " ha construido una casa en la propiedad " + ip);
                Diario.getInstance().ocurreEvento("Casas: " + propiedades.get(ip).getNumCasas() + " || Hoteles: " + propiedades.get(ip).getNumHoteles());
            }
        }
        return result;
    }
    
    boolean construirHotel(int ip){
        boolean result = false;
        
        if(existeLaPropiedad(ip)){
            CasillaCalle propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            //puedoEdificarHotel = false;
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precio)){
                if(propiedad.getNumHoteles()<getHotelesMax()){
                    if(propiedad.getNumCasas()>=getCasasPorHotel()){
                        puedoEdificarHotel=true;
                    }else{
                        System.out.println("\nERROR: No se puede construir hotel. Numero de casas < 4\n");
                    }
                }else{
                    System.out.println("\nERROR: Número de hoteles máximo alcanzado.\n");
                }
            }else{
                System.out.println("\nERROR: No tienes saldo suficiente.\n");
            }
            
            if(puedoEdificarHotel){
                result = propiedad.construirHotel(this);
                result = true;
                
                propiedad.derruirCasas(CasasPorHotel, this);
                
                Diario.getInstance().ocurreEvento(nombre + " ha construido un hotel en la propiedad  " + ip);
                Diario.getInstance().ocurreEvento("Casas: " + propiedades.get(ip).getNumCasas() + " || Hoteles: " + propiedades.get(ip).getNumHoteles());
            }
        }
        return result;
    }
    
    boolean enBancarrota(){
        return saldo <= 0;
    }
    
    /**
     * Método que comprueba que el índice de la propiedad sea valido.
     * @param ip define el índice que queremos comprobar.
     * @return true: el índice es valido.
     */
    private boolean existeLaPropiedad(int ip){
        return ip >=0 && ip<propiedades.size();
    }
    
    int getCasasMax(){
        return CasasMax;
    }
    
    static int getCasasPorHotel(){
        return CasasPorHotel;
    }
    
    public int getCasillaActual(){
        return casillaActual;
    } 
    
    int getHotelesMax(){
        return HotelesMax;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    private static float getPremioPasoSalida(){
        return PasoPorSalida;
    }
    
    public ArrayList<CasillaCalle> getPropiedades(){
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        return puedeComprar;
    }
    
    public float getSaldo(){
        return saldo;
    }
    
    /**
     * Método que modifica el saldo de un jugador.
     * @param cantidad define la cantidad a modificar en el saldo.
     * @return true: se ha modificado el saldo.
     */
    boolean modificaSaldo(float cantidad){
        saldo = saldo+cantidad;
        if(cantidad<0){
            Diario.getInstance().ocurreEvento(nombre + " pierde " + cantidad + "€");
            Diario.getInstance().ocurreEvento("Nuevo saldo: " + saldo);
        }else if(cantidad >0){
            Diario.getInstance().ocurreEvento(nombre + " gana " + cantidad + "€");
            Diario.getInstance().ocurreEvento("Nuevo saldo: " + saldo);
        }
        return true;
    }
    
    /**
     * Método que mueve al jugador a una casilla.
     * @param numCasilla define la casilla a la que se mueve el jugador.
     * @return true: se ha movido el jugador.
     */
    boolean moverACasilla(int numCasilla){
        casillaActual = numCasilla;
        puedeComprar = false;
        Diario.getInstance().ocurreEvento(nombre + " se ha movido a la casilla " + numCasilla);
        return true;
    }
    
    /**
     * Método para modificar el saldo del jugador con la cantidad a pagar.
     * @param cantidad define la cantidad que se tiene que pagar.
     * @return true: se ha modificado el saldo.
     */
    boolean paga(float cantidad){
        return modificaSaldo(cantidad*-1); 
    }
    
    /**
     * Método para pagar el alquiler al caer en una casilla.
     * @param cantidad define la cantidad que se tiene que pagar.
     * @return true: se ha pagado correctamente.
     */
    boolean pagaAlquiler(float cantidad){
        return paga(cantidad); 
    }
    
    /**
     * Método para cobrar la cantidad correspondiente por pasar por la casilla de salida.
     * @return true: se ha recibido la cantidad correctamente.
     */
    boolean pasaPorSalida(){
        recibe(PasoPorSalida);
        Diario.getInstance().ocurreEvento(nombre + " pasa por salida. Recibe: " + PasoPorSalida);
        return true;
    }
    
    /**
     * Método que comprueba si se puede comprar una propiedad.
     * @return puedeComprar: true si se puede comprar esa propiedad.
     */
    boolean puedeComprarCasilla(){
        puedeComprar = true;
        return puedeComprar;
    }
    
    private boolean puedoEdificarCasa(CasillaCalle propiedad){
        boolean puedo = false;
        if(puedoGastar(propiedad.getPrecioEdificar())){
            if(propiedad.getNumCasas() < getCasasMax()){
                puedo = true;
            }
        }
        return puedo;
    }
    
    private boolean puedoEdificarHotel(CasillaCalle propiedad){
        boolean puedo = false;
        if(puedoGastar(propiedad.getPrecioEdificar())){
            if(propiedad.getNumHoteles() < getHotelesMax()){
                if(propiedad.getNumCasas() >= CasasPorHotel){
                    puedo = true;
                }
            }
        }
        return puedo;
    }
    
    /**
     * Método que indica si el saldo me permite gastar una cantidad.
     * @param precio define la cantidad que se quiere gastar
     * @return true: el precio es menor que el precio por lo que se puede gastar.
     */
    private boolean puedoGastar(float precio){
        boolean comprobar;
        if(precio <= saldo){
            comprobar = true;
        }else{
            comprobar = false;
        }
        return comprobar;
    }
    
    /**
     * Método para recibir una cantidad.
     * @param cantidad define la cantidad que se recibe.
     * @return true: se ha modificado el saldo.
     */
    boolean recibe(float cantidad){
        return modificaSaldo(cantidad); 
    }
    
    /**
     * Método que indica si el jugador tiene propiedades.
     * @return true: el jugador tiene propiedades.
     */
    boolean tieneAlgoQueGestionar(){
        boolean gestiona;
        if(propiedades.size()>=0){
            gestiona = true;
        }else{
            gestiona = false;
        }
        return gestiona;
    }
    
    public JugadorEspeculador convertir(){
        //JugadorEspeculador especulador = new JugadorEspeculador(this);
        return new JugadorEspeculador(this);
    }
    
    /*public void actualizaPropiedadesPorConversion(){
        for(CasillaCalle casilla : propiedades){
            casilla.actualizaPropietarioPorConversion(this);
        }
    }*/
    
    public boolean isEspeculador(){
        return esEspeculador;
    }
    
    @Override
    public String toString(){
        String separador;
        return " [ " + getNombre() + " || Casilla actual: " + casillaActual + 
                " || Saldo: " + saldo + " ] ";
    }
  
}
