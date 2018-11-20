/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Srv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SrvMulti {
    private static final int port = 10000;
    private static final int numClientes = 100;
      
    public static void main(String[] args) throws InterruptedException {
        Vector<Hilo> hilos = new Vector<>();
        
        try {
            ServerSocket ss = new ServerSocket(port, numClientes);
            
            while (true){
                try {
                    Socket s = ss.accept(); // aceptas conexion
                    System.out.println("Aceptada la conexión " + ss);
		    Hilo sh = new Hilo(s); // crea el hilo del servidor
                    hilos.add(sh);
                    new Thread(sh).start(); // empieza el hilo
		} catch (IOException e) {
                    System.out.println("No se puede conectar con el puerto " + port +": " + e.getMessage());
		}
            }
        } catch (IOException ex) {
            System.out.println("catch del server multi " + ex);
            //Logger.getLogger(Jawa3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
