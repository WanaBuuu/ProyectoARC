package Srv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import paquete.Paquete;

/**
 * @brief Clase principal
 * @author G 1.1
 */
public class SrvMulti {

    private static final int numClientes = 10;
    /**
     * @brief Metodo main del programa
     * @param args Argumentos
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        
        PantallaServidor pantalla = new PantallaServidor();
        pantalla.setVisible(true);	
    }
    
    /**
     * @brief Lanza la aplicacion del servidor
     * @param nConexiones Numero de conexiones
     * @throws InterruptedException
     */
    public void StartServidor(int nConexiones) throws InterruptedException{
        Vector<Hilo> hilos = new Vector<>();
        int port = 10000;
	int numConexiones = 0;
     
        try {
            ServerSocket ss = new ServerSocket(port, numClientes);

            while (true && numConexiones < numClientes){
                try {
                    Socket s = ss.accept(); // acepta conexion
                    System.out.println("Aceptada la conexión " + ss);
                    numConexiones++;
                    Hilo sh = new Hilo(s); // crea el hilo del servidor
                    hilos.add(sh);
                } catch (IOException e) {
                    System.out.println("No se puede conectar con el puerto " + port +": " + e.getMessage());
                }
            }

            for(int i = 0; i< hilos.size(); i++){
                new Thread(hilos.get(i)).start();
            }
        } catch (IOException ex) {
            System.out.println("catch del server multi " + ex);
        }	

        System.out.println("*** Se han establecido todas las conexiones ***\n*** Empieza la simulación ***");

        //Espera a que le lleguen los paquetes
        while(hilos.get(0).getPaquete() == null) {
            Thread.sleep(100);
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