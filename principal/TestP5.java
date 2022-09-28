/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package principal;

import GUI.*;
import civitas.CivitasJuego;
import controladorCivitas.Controlador;
import java.awt.Frame;
import java.util.ArrayList;

/**
 *
 * @author celiaa
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here       
        CivitasView civitas = new CivitasView();
        
        Dado.createInstance(civitas);
        
        CapturaNombres nombres = new CapturaNombres(civitas,true);
        
        ArrayList<String> jugadores = new ArrayList(nombres.getNombres());
        
        CivitasJuego juego = new CivitasJuego(jugadores,false);
        
        Controlador controlador = new Controlador(juego,civitas);
        
        civitas.setCivitasJuego(juego);
        
        civitas.actualiza();
        
        controlador.juega();
        
        
    }
    
}
