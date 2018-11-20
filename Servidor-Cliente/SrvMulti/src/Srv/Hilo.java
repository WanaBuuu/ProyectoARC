package Srv;

import paquete.Paquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class Hilo implements Runnable{
    private final Socket socket;
    private ObjectOutputStream oos;
    private double ownTime;
    private Paquete p;
    
    public Hilo(Socket socket) {
        this.socket = socket;
        
        try {
	    this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            //this.ois = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
	    System.err.println(ex);
        }
    }
    
    public Paquete getPaquete() {
        return this.p;
    }
    public void setPaquete(Paquete p) {
        this.p = p;
    }
    
    public void envPaq(Paquete p){ //reenvia el paquete que recibe
       try {
            for(int j = 0; j < p.getnGrupos(); j++){
               for(int i = 0; i < p.getnClientes(); i++){ //creo que este bucle va fuera.. quitar de clase Cliente el numeroDeClientes. ********
                    if(p.getNumCliente() != i && p.getNumGrupo() == j){ 
                        //System.out.println(String.valueOf(p.getNumCliente()) + i + " " + String.valueOf(p.getNumGrupo())  + j);
                        p.setNumCliente(i);
                        oos.writeObject(p);
                        System.out.println("SERVIDOR reenvia cordenadas " + p.getCoordenadas() + " al CLIENTE " + p.getNumCliente() + " del grupo " + p.getNumGrupo());
                    }
                }
            }
           
           Thread.sleep(10000); // aqi deberia ponerse a dormir hasta que reciba la confirmacion de todos los clientes
        } catch (IOException | InterruptedException ex) {
           System.out.println("FALLA ENVIO PAQUETE");
        // Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    public void envConfirmacion(Paquete p){
        try {
            for(int j = 0; j < p.getnGrupos(); j++){
               for(int i = 0; i < p.getnClientes(); i++){
                    if(p.getNumCliente() == i && p.getNumGrupo() == j){ 
                        //System.out.println(String.valueOf(p.getNumCliente()) + i + " " + String.valueOf(p.getNumGrupo())  + j);
                        //Envio T2
                        //p.setTimestamp((System.currentTimeMillis()) - ownTime);
                        p.setNumCliente(i);
                        oos.writeObject(p);
                        System.out.println("T2: " + ((System.currentTimeMillis()) - ownTime));
                        System.out.println("SERVIDOR envia ACK al CLIENTE " + p.getNumCliente());
                    }
                }
            }
           
           Thread.sleep(10000); // aqi deberia ponerse a dormir hasta que reciba la confirmacion de todos los clientes
        } catch (IOException | InterruptedException ex) {
           System.out.println("FALLA ENVIO PAQUETE ACK");
        // Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    @Override
    public void run() {
        ObjectInputStream ois = null;
        Paquete ack = new Paquete();
        
        try {
            ois = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        while (true){
            try {
                // aqui lee el paquete que le envia el cliente
                p = (Paquete)ois.readObject();
                
                ownTime = p.getTimestamp();
                /*if(p != null)
                    ownTime = p.getTimestamp();*/
                
                if (!p.isAck()){
                    System.out.println("SERVIDOR intercepta coordenadas "+ p.getCoordenadas() + " del CLIENTE " + p.getNumCliente() + " del grupo " + p.getNumGrupo());   
                    // Enviar el paquete al resto del grupo menos al que se lo ha enviado si no es 
                    this.envPaq(p);
                    System.out.println("Paquete normal");
                }
                else{
                    /* Crea el paquete ack*/
                    ack.setAck(true);
                    ack.setNumCliente(p.getNumCliente());
                    ack.setNumGrupo(p.getNumGrupo());
                    ack.setnClientes(p.getnClientes());
                    ack.setnGrupos(p.getnGrupos());
                    
                    this.envConfirmacion(ack);
                    System.out.println("paquete ack");
                }
                   
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("falla run hilo");
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
}
