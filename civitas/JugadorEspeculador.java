/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package civitas;

/**
 *
 * @author celiaa
 */
public class JugadorEspeculador extends Jugador{
    
    private static int FactorEspeculador = 2;
    private boolean esEspeculador;
    
    JugadorEspeculador(Jugador jugador){
        super(jugador);
        actualizaPropiedadesPorConversion();
        esEspeculador = true;
    }
    
    private void actualizaPropiedadesPorConversion(){
        for(int i=0; i<super.getPropiedades().size(); i++){
            super.getPropiedades().get(i).actualizaPropietarioPorConversion(this);
        }
    }
    
    @Override
    public boolean isEspeculador(){
        return esEspeculador;
    }
    
    @Override
    protected int getCasasMax(){
        return (CasasMax*FactorEspeculador);
    }
    
    @Override
    protected int getHotelesMax(){
        return (HotelesMax*FactorEspeculador);
    }
    
    @Override
    public boolean paga(float cantidad){
        return super.paga(cantidad/2);
    }
    
    @Override
    public String toString(){
        String separador;
        return " [ " + getNombre() + " || Casilla actual: " + super.getCasillaActual() + 
                " || Saldo: " + super.getSaldo() + " ] ";
    }
    
}
