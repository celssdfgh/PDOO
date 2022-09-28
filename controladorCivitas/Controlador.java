/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladorCivitas;

import GUI.CivitasView;
import civitas.*;
import civitas.OperacionJuego;


/**
 *
 * @author celiaa
 */
public class Controlador {
    private CivitasJuego juegoModel;
    private CivitasView vista;
    
    private static String separador = "=====================";
    
    public Controlador(CivitasJuego juegoModel, CivitasView vista){
        this.juegoModel = juegoModel;
        this.vista = vista;
    }
    
    public void juega(){
        while(!juegoModel.finalDelJuego()){
            
            //vista.mostrarEstado();
            vista.actualiza();
            
            vista.pausa();
            
            OperacionJuego operacion = juegoModel.siguientePaso();
            vista.mostrarSiguienteOperacion(operacion);
            
            if(operacion!=OperacionJuego.PASAR_TURNO){
                vista.mostrarEventos();
            }
             
            if(!juegoModel.finalDelJuego()){
                switch(operacion){
                    case COMPRAR:
                        Respuesta r = vista.comprar();
                        if(r == Respuesta.SI){
                            juegoModel.comprar();
                        }
                        juegoModel.siguientePasoCompletado(operacion);
                        break;
                    case GESTIONAR:
                        OperacionInmobiliaria op = vista.elegirOperacion();
                        if(op != OperacionInmobiliaria.TERMINAR){
                            int ip = vista.elegirPropiedad();
                            
                            GestionInmobiliaria gi = new GestionInmobiliaria(op,ip);
                            if(op == OperacionInmobiliaria.CONSTRUIR_CASA){
                                juegoModel.construirCasa(ip);
                            }
                            if(op == OperacionInmobiliaria.CONSTRUIR_HOTEL){
                                juegoModel.construirHotel(ip);
                            }
                        }else if (op == OperacionInmobiliaria.TERMINAR){
                            juegoModel.siguientePasoCompletado(operacion);
                        }
                        break;
                }
            } 
        }
        System.out.println("=====FIN DEL JUEGO=====");
        juegoModel.ranking();
        //vista.mostrarEstado();
        vista.actualiza();
    }
    
}
