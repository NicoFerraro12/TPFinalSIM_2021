package front.vista.simulacion;

import objects.Controller;

public class Aplicacion {
    public static void main (String args[])
    {
        iniciarAplicacion();
    }
    
    private static void iniciarAplicacion()
    {
        Controller controlador;
        controlador = Controller.getInstance();
        controlador.mostrarVentana();
    }
}
