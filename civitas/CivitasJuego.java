/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

import GUI.Dado;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author celiaa
 */
public class CivitasJuego {
    private int indiceJugadorActual;
    private MazoSorpresas mazo;
    private Tablero tablero;
    private EstadoJuego estado;
    private GestorEstados gestor;
    private ArrayList<Jugador> jugadores;
      
    public CivitasJuego(ArrayList<String> nombres, boolean debug){
        //Inicializa jugadores y añade un jugador por cada nombre
        jugadores = new ArrayList();
        for(int i=0; i<nombres.size(); i++){
            Jugador jugador = new Jugador(nombres.get(i));
            jugadores.add(jugador);
        }
        
        //Crea gestor  estados y fija como estado inicial el actual
        gestor = new GestorEstados();
        estado = gestor.estadoInicial();
        
        //Pone el dado en modo debug/nodebug dependiendo del parametro
        Dado.getInstance();
        Dado.getInstance().setDebug(debug);
        
        //Inicializa el indice del jugador actual
        indiceJugadorActual = Dado.getInstance().quienEmpieza(jugadores.size());
        
        //Crea mazo sorpresas en modo debug/nodebug dependiendo del parametro
        mazo = new MazoSorpresas(debug);
        
        //Crea el tablero y lo inicializa
        tablero = new Tablero();
        inicializaTablero(mazo);
        
        //Inicializa el mazo de sorpresas
        inicializaMazoSorpresas();
        
    }
    
    private void avanzaJugador(){
        Jugador jugadorActual = getJugadorActual();
        int posicionActual = jugadorActual.getCasillaActual();
        int tirada = Dado.getInstance().tirar();
        int posicionNueva = tablero.nuevaPosicion(posicionActual, tirada);
        Casilla casilla = tablero.getCasilla(posicionNueva);
        
        contabilizarPasosPorSalida();
        
        jugadorActual.moverACasilla(posicionNueva);
        casilla.recibeJugador(indiceJugadorActual, jugadores);
    }
    
    public boolean comprar(){
        boolean res;
        Jugador jugadorActual = getJugadorActual();
        int numCasillaActual = jugadorActual.getCasillaActual();
        
        Casilla casilla = tablero.getCasilla(numCasillaActual);
        CasillaCalle calle = (CasillaCalle)casilla;
        
        res = jugadorActual.comprar(calle);
        return res;
    }
    
    public boolean construirCasa(int ip){
        return jugadores.get(indiceJugadorActual).construirCasa(ip);
    }
    
    public boolean construirHotel(int ip){
        return jugadores.get(indiceJugadorActual).construirHotel(ip);
    }
    
    private void contabilizarPasosPorSalida(){
        if(tablero.computarPasoPorSalida()){
            getJugadorActual().pasaPorSalida();
            //jugadores.get(indiceJugadorActual).pasaPorSalida();
        }
    }
    
    public boolean finalDelJuego(){
        boolean fin = false;
        for(int i=0; i < jugadores.size();i++){
            if(jugadores.get(i).enBancarrota()){
                fin = true;
            }
        }
        return fin;
    }
    
    public int getIndiceJugadorActual(){
        return indiceJugadorActual;
    }
    
    public Jugador getJugadorActual(){
        return jugadores.get(getIndiceJugadorActual());
    }
    
    public Casilla getCasillaActual(){
        return tablero.getCasilla(jugadores.get(indiceJugadorActual).getCasillaActual());
    }
    
    public ArrayList<Jugador> getJugadores(){ //Devuelve el to string
        return jugadores;
    }
    
    public Tablero getTablero(){
        return tablero;
    }
    
    private void inicializaMazoSorpresas(){
        mazo.alMazo(new SorpresaPorCasaHotel("Pagas 50€ por cada casa y hotel que hayas construido", -50));
        mazo.alMazo(new SorpresaPagarCobrar("Hay una fuga de agua y tienes que pagarle 260€ a un fontanero para que lo arregle", -260));
        mazo.alMazo(new SorpresaPorCasaHotel("Reformas. Pagas 20€ por cada casa/hotel de tu propiedad", -20));
        mazo.alMazo(new SorpresaConvertirme());
        mazo.alMazo(new SorpresaPagarCobrar("Te has encontrado 200€ en la calle. Ahora son tuyos", 200));
        mazo.alMazo(new SorpresaPagarCobrar("Pagas 150€ por la factura de la luz de este mes", -150));
        mazo.alMazo(new SorpresaPorCasaHotel("Enhorabuena! Recibes 25€ por cada casa/hotel de tu propiedad", 25));
        mazo.alMazo(new SorpresaPagarCobrar("Pagas 200€ por una multa de trafico", -200));
        mazo.alMazo(new SorpresaPagarCobrar("Enhorabuena has ganado un concurso. Cobras 100€", 100));
        mazo.alMazo(new SorpresaPorCasaHotel("Recibes 40€ por cada casa/hotel que hayas construido", 40));
        mazo.alMazo(new SorpresaPagarCobrar("Has visitado a tu abuela y te ha dado 150€", 150));
        
        
    }
    
    private void inicializaTablero(MazoSorpresas mazo){ 
        tablero.añadeCasilla(new CasillaCalle("Camino de ronda", 50, 55, 40));
        tablero.añadeCasilla(new CasillaCalle("Recogidas", 100, 120, 90));
        tablero.añadeCasilla(new CasillaSorpresa("¡SORPRESA!",mazo));
        tablero.añadeCasilla(new CasillaCalle("Puentezuelas", 150, 170, 125));
        tablero.añadeCasilla(new CasillaCalle("Reyes Católicos", 200, 220, 175));
        tablero.añadeCasilla(new CasillaSorpresa("¡SORPRESA!", mazo));
        tablero.añadeCasilla(new CasillaCalle("Arabial", 250, 260, 200));
        tablero.añadeCasilla(new CasillaCalle("Plazanueva", 300, 310, 250));
        tablero.añadeCasilla(new CasillaCalle("Carcel Baja", 350, 360, 325));
        tablero.añadeCasilla(new CasillaCalle("Avenida de la constitución", 400, 410, 340));
        tablero.añadeCasilla(new CasillaSorpresa("¡SORPRESA!", mazo));
        tablero.añadeCasilla(new CasillaCalle("Paseo de los tristes", 450, 460, 360));
        tablero.añadeCasilla(new CasillaCalle("Cuesta del Chapiz", 500, 520, 380));    
        tablero.añadeCasilla(new CasillaCalle("Sócrates", 550, 565, 400));
        tablero.añadeCasilla(new CasillaDescanso("PARKING"));
        tablero.añadeCasilla(new CasillaCalle("Pedro Antonio", 600, 600, 420));
        tablero.añadeCasilla(new CasillaCalle("Villarejo", 650, 640, 450));
        tablero.añadeCasilla(new CasillaSorpresa("¡SORPRESA!", mazo));
        tablero.añadeCasilla(new CasillaCalle("Mendez Nuñez", 700, 650, 500));
       
    }
    
    private void pasarTurno(){
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
    }
    
    public ArrayList<Jugador> ranking(){
        ArrayList<Jugador>ranking = jugadores;
        for(int i = 0; i < ranking.size() - 1; i++) {
            for(int j = i + 1; j < ranking.size(); j++) {    
                if(ranking.get(i).compareTo(ranking.get(j)) < 0) {
                    Jugador aux = new Jugador(ranking.get(i));
                    ranking.set(i, ranking.get(j));
                    ranking.set(j, aux);
                }
            }
        }
        return ranking;
    }
    
    public OperacionJuego siguientePaso(){
        Jugador jugadorActual = getJugadorActual();
        OperacionJuego operacion = gestor.siguienteOperacion(jugadorActual, estado);
        
        if(operacion==OperacionJuego.PASAR_TURNO){
            pasarTurno();
            siguientePasoCompletado(operacion);
        }else if(operacion==OperacionJuego.AVANZAR){
            avanzaJugador();
            siguientePasoCompletado(operacion);
        }
        return operacion;
    }
    
    public void siguientePasoCompletado(OperacionJuego operacion){
        estado = gestor.siguienteEstado(jugadores.get(indiceJugadorActual), estado, operacion);
    }
    
    
}
