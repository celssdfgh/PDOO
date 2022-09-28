/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package controladorCivitas;

import civitas.*;
import java.util.ArrayList;
import java.util.Scanner;
//import GUI.VistaTextual;

/**
 *
 * @author celiaa
 */


//public class JuegoTexto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<String> nombres = new ArrayList();
        int n=0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca el numero de jugadores (2-4):");
        if (n>=2 || n<=4){
            n = teclado.nextInt();
        }
        
        System.out.println("Introduzca el nombre de los jugadores");
        for(int i=0; i<n; i++){
            String nombre;
            System.out.println("Jugador " + (i+1) + ":");
            nombre = teclado.next();
            nombres.add(nombre);
        }
        System.out.println("\nJUGADORES: " + nombres + "\n");

        CivitasJuego juego = new CivitasJuego(nombres,false);
        VistaTextual vista = new VistaTextual(juego);
        Controlador controlador = new Controlador(juego,vista);
        
        controlador.juega();
        
        
        
    }
    
}
