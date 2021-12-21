package objects;

import front.vista.simulacion.VentanaPrincipal;

public class Controller {
    
    private static Controller controller;
    static Calculator calculator;
    private final VentanaPrincipal tablaSimulacionSet;
    
    private Controller ()
    {
        setLookAndFeel();
        tablaSimulacionSet = new VentanaPrincipal(this);
    }
    
    public static Controller getInstance() 
    {
        if (controller == null) {
            controller = new Controller();
        }         
        return controller;
    }
    
    public void mostrarVentana()
    {
        tablaSimulacionSet.setLocationRelativeTo(null);
        tablaSimulacionSet.setVisible(true);
    }
    
    
    private void setLookAndFeel() {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
