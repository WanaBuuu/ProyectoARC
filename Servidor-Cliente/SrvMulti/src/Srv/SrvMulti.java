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
import paquete.Paquete;

public class SrvMulti {
    private static final int port = 10000;
    private static final int numClientes = 100;
      
    public static void main(String[] args) throws InterruptedException {
        Vector<Hilo> hilos = new Vector<>();
        
	int numConexiones = 0;
		
        try {
            ServerSocket ss = new ServerSocket(port, numClientes);
            
            while (true && numConexiones < numClientes){
                try {
                    Socket s = ss.accept(); // aceptas conexion
                    System.out.println("Aceptada la conexión " + ss);
                    numConexiones++;
                    Hilo sh = new Hilo(s); // crea el hilo del servidor
                    hilos.add(sh);
                   // new Thread(sh).start(); // empieza el hilo
		} catch (IOException e) {
                    System.out.println("No se puede conectar con el puerto " + port +": " + e.getMessage());
		}
            }
            
            for(int i = 0; i< hilos.size(); i++){
                new Thread(hilos.get(i)).start();
            }
            
        } catch (IOException ex) {
            System.out.println("catch del server multi " + ex);
            //Logger.getLogger(Jawa3.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		//añadido mipumu
		System.out.println("*** Se han establecido todas las conexiones ***\n*** Empieza la simulación ***");
            
            //Espera a que le lleguen los paquetes
            while(hilos.get(0).getPaquete() == null) {
                Thread.sleep(1000);
            }
            //Envia las coordenadas a cada vecino
            for(int i = 0; i < hilos.size(); i++) {
                
                Paquete paqDifundir = hilos.get(i).getPaquete();
                
                for(int j = 0; j < hilos.size(); j++) {
                    if(paqDifundir.getNumCliente() != hilos.get(j).getPaquete().getNumCliente()) {
                        hilos.get(j).envPaq(paqDifundir);
                    }
                }
            }
			
			
		
    }
}
