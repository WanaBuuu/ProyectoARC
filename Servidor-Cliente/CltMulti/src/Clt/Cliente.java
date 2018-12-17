package Clt;

import paquete.Paquete;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @brief Clase cliente principal
 * 
 * @author G 1.1
 */
public class Cliente  implements Runnable { // extedns mihilo 
    
    private int port;
    private String ip;
    private ObjectOutputStream oos;
    private int pausa;
    private int grupo, id, nGrupos, nClientes;
    private Socket cliente;
    private double t1;

    /**
     *
     * @param ip
     * @param port
     * @param pausa
     * @param grupo
     * @param id
     * @param nGrupos
     * @param nClientes
     */
    public Cliente(String ip, int port, int pausa,int grupo,int id, int nGrupos, int nClientes) {
	this.ip = ip;
	this.port = port;
	this.pausa = pausa;
        this.grupo = grupo;
        this.id = id;
        this.nClientes = nClientes;
        this.nGrupos = nGrupos;
	
        try {
            this.cliente = new Socket(ip, port);
	    this.oos = new ObjectOutputStream(cliente.getOutputStream());
            //this.ois = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
	    System.err.println(ex);
        }
        
    }
  
    /**
     *
     * @param p
     */
    public void envPaq(Paquete p){
	try {
	    oos.writeObject(p);
            System.out.println("CLIENTE " + String.valueOf(id) + " del grupo " + String.valueOf(grupo) + " ha enviado coordenadas " + p.getCoordenadas() + " al SERVIDOR" );
	    Thread.sleep(pausa); 
	} catch (IOException | InterruptedException ex) {
	    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    /**
     *
     * @param ack
     */
    public void envConfirmacion(Paquete ack){
        try {
	    oos.writeObject(ack);
            System.out.println("CLIENTE envia confirmación de recepción de el paquete reenviado por el SERVIDOR");
	    Thread.sleep(pausa); 
	} catch (IOException | InterruptedException ex) {
	    Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    @Override
    public void run() {
	Paquete p = new Paquete();
        Paquete ack = new Paquete();
        int x,y,z, contadorACK = 0;
        ObjectInputStream ois = null;
        boolean PInicion = false;

        // Rellenamos el paquete que contendra las coordenadas
        // crea coordenas aleatorias
        x = (int) (Math.random() * 255 + 1);
        y = (int) (Math.random() * 255 + 1);
        z = (int) (Math.random() * 255 + 1);
        
        // rellena el paquete con la informacion necesaria
        p.setcoordenadas(String.valueOf(x)+"/"+String.valueOf(y)+"/"+String.valueOf(z));
        p.setNumCliente(id);
        p.setNumGrupo(grupo);
        p.setnClientes(nClientes);
        p.setnGrupos(nGrupos);
        p.setTimestamp(System.currentTimeMillis());
        
        // Rellenamos el paquete para responder al servidor que nos ha llegado su reenvio
        ack.setNumCliente(id);
        ack.setNumGrupo(grupo);
        ack.setnClientes(nClientes);
        ack.setnGrupos(nGrupos);
        ack.setAck(true);
        
	while (true){
            
            if (!PInicion){
                this.envPaq(p); // envia el paquete creado
                PInicion = true;
            }
            
            try {
                ois = new ObjectInputStream(this.cliente.getInputStream());
                
            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                // aqui lee el paquete que le envia el servidor
                p = (Paquete)ois.readObject();
                
                if (!p.isAck()){
                    t1 = p.getTimestamp() - System.currentTimeMillis();
                    System.out.println("T1:" + t1);
                    System.out.println("CLIENTE intercepta coordenadas "+ p.getCoordenadas() + " del cliente " + p.getNumCliente() + " del grupo " + p.getNumGrupo() + " que le reenvia el servidor");
                    
                    // Eviar confirmacion de receptor del servidor
                    this.envConfirmacion(ack);
                }
                else{   
                }
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("falla run hilo");
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, e);
            }
           
            contadorACK ++;
            double d = (p.getTimestamp() - t1)/2;
            System.out.println("Tengo todos los ACK recibidos ");
            System.out.println("Timestamp (d):\n");

            if (contadorACK < (this.nClientes)){ // (p.getnClientes() - 1)
                System.out.println("Recibido todos los ACK, me desconecto");
                //System.exit(0);
            }
	}
    } 
}
