package paquete;

import java.io.Serializable;

/**
 * @brief Clase paquete cliente
 * @author G 1.1
 */
public class Paquete implements Serializable{ // Usamos esto por que cuando vamos a enviar a traves de la red y se necesita convertir a byts
    
    private String coordenadas = "";
    private int numCliente, numGrupo, nGrupos, nClientes;
    private boolean ack = false;
    private double timestamp;
    
    /**
     * @brief Constructor de paquete con parametros
     * @param coordenadas String con las oordenadas
     * @param numCliente Numero de cliente al que pertenece
     * @param numGrupo Numero de grupo al que pertenece
     * @param nGrupos Numero de grupos
     * @param nClientes Numero de clientes
     */
    public Paquete(String coordenadas, int numCliente, int numGrupo, int nGrupos, int nClientes) { // creo que no lo usamos
	this.coordenadas = coordenadas;
        this.numCliente = numCliente;
        this.numGrupo = numGrupo;
        this.nClientes = nClientes;
        this.nGrupos = nGrupos;
    }
    
    /**
     * @brief Constructor por defecto de paquete
     */
    public Paquete(){}
    
    /**
     * @brief Metodo getter
     * @return Valor del Timestamp
     */
    public double getTimestamp(){
        return this.timestamp;
    }
    
    /**
     * @brief Metodo setter
     * @param timestamp Valor a asignar
     */
    public void setTimestamp( double timestamp){
        this.timestamp = timestamp;
    }
    
    /**
     * @brief Metodo getter
     * @return Si es un ack o no
     */
    public boolean isAck() {
        return ack;
    }

    /**
     * @brief Metodo setter
     * @param ack Valor ack
     */
    public void setAck(boolean ack) {
        this.ack = ack;
    }

    /**
     * @brief Metodo getter
     * @return Numero de grupos
     */
    public int getnGrupos() {
        return nGrupos;
    }

    /**
     * @brief Metodo setter
     * @param nGrupos Numero de grupos a asignar
     */
    public void setnGrupos(int nGrupos) {
        this.nGrupos = nGrupos;
    }

    /**
     * @brief Metodo getter
     * @return Numero de clientes 
     */
    public int getnClientes() {
        return nClientes;
    }

    /**
     * @brief Metodo setter
     * @param nClientes Numero de clientes
     */
    public void setnClientes(int nClientes) {
        this.nClientes = nClientes;
    }

    /**
     * @brief Metodo getter
     * @return Devuelve el numero de cliente
     */
    public int getNumCliente() {
        return numCliente;
    }

    /**
     * @brief Metodo setter
     * @param numCliente Numero de cliente
     */
    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    /**
     * @brief Metodo getter
     * @return Numero de grupo al que pertenece
     */
    public int getNumGrupo() {
        return numGrupo;
    }

    /**
     * @brief Metodo setter
     * @param numGrupo Numero de grupo al que pertenece
     */
    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }

    /**
     * @brief Metodo getter
     * @return Coordenadas asignadas a ese cliente
     */
    public String getCoordenadas() {
	return coordenadas;
    }

    /**
     * @brief Metodo getter
     * @param texto Asigna las coordenadas 
     */
    public void setcoordenadas(String texto) {
	this.coordenadas = texto;
    }
}
