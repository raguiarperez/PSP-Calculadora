/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadoraserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author rafaguiarp
 */
public class CalculadoraServer extends Thread{
    
    public Socket clienteSocket;
    
    public CalculadoraServer(Socket socket){
        clienteSocket=socket;
    }
    private final String[] operadores = {" + ", " - ", " * ", " / "};
    public void run(){
        
        try{
         System.out.println("Conexion recibida");
            InputStream is=clienteSocket.getInputStream();
            OutputStream os=clienteSocket.getOutputStream();
            
            float resultado=0;
            byte[] mensaje=new byte[200];
            is.read(mensaje);
            
            String cuenta= new String(mensaje);
            String[]operacion=cuenta.split(",");
            System.out.println(operacion[0]+" "+operacion[1]+" "+operacion[2]);
            

                switch(operacion[1]){
                    case "+": resultado = Float.valueOf(operacion[0]) + Float.valueOf(operacion[2]);
                    break;
                    case "-": resultado = Float.valueOf(operacion[0]) - Float.valueOf(operacion[2]);
                    break;
                    case "*": resultado = Float.valueOf(operacion[0]) * Float.valueOf(operacion[2]);
                    break;
                    case "/": resultado = Float.valueOf(operacion[0]) / Float.valueOf(operacion[2]);
                    break;
            
        }
            System.out.println(resultado);
            String respuesta=String.valueOf(resultado);
            os.write(respuesta.getBytes());
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        int puerto=Integer.parseInt(JOptionPane.showInputDialog(null,"Introduce el puerto deseado:","7777"));
        System.out.println("Creando socket servidor");
        ServerSocket serverSocket=new ServerSocket();
        System.out.println("Realizando el bind");
        String respuesta=null;
        InetSocketAddress addr=new InetSocketAddress("localhost",puerto);
        serverSocket.bind(addr);
        while(serverSocket!=null){
            Socket newSocket= serverSocket.accept();
            CalculadoraServer hilo=new CalculadoraServer(newSocket);
            hilo.start();
        }
    }
}