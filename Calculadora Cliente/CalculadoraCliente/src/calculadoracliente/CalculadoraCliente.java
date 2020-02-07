/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoracliente;

import javax.swing.JOptionPane;

/**
 *
 * @author rafaguiarp
 */
public class CalculadoraCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip=JOptionPane.showInputDialog(null,"Introduce la Ip deseada:","localhost");
        int puerto=Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce el puerto deseado:","7777"));
            
        Ventana obx=new Ventana(ip,puerto);
        obx.setVisible(true);
    }
    
}
