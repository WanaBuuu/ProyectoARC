/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.Serializable;

/**
 * @brief Clase principal
 * @author G 1.1
 */
public class Paquete implements Serializable{ // Usamos esto por que cuando vamos a enviar a traves de la red y se necesita convertir a byts
    
    private String coordenadas = "";
    private int numCliente, numGrupo, nGrupos, nClientes;
    private boolean ack = false;
    private double timestamp;
    
    /**
     * @brief Constructor de la clase con parametros
     * @param coordenadas Coordenadas 
     * @param numCliente Numero de cliente
     * @param numGrupo Numero de grupo
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
     * @brief Constructor de clase vacio
     */
    public Paquete(){}
    
    /**
     * @brief Metodo getter
     * @return Devuelve el timestamp
     */
    public double getTimestamp(){
        return this.timestamp;
    }

    /**
     * @brief Metodo setter
     * @param timestamp Valor a asignar
     */
    public void setTimestamp(double timestamp){
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
     * @param ack Valor a asignar
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
     * @param nGrupos Numero de grupos
     */
    public void setnGrupos(int nGrupos) {
        this.nGrupos = nGrupos;
    }

    /**
     * @brief Metodo getter
     * @return Devuelve el numero de clientes
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
     * @brief Metodo setter
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
     * @return Devuelve el numero de grupo
     */
    public int getNumGrupo() {
        return numGrupo;
    }

    /**
     * @brief Metodo setter
     * @param numGrupo Inserta el numero de grupo
     */
    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }

    /**
     * @brief Metodo getter
     * @return Devuelve las coordenadas
     */
    public String getCoordenadas() {
	return coordenadas;
    }

    /**
     * @brief Metodo setter
     * @param texto Coordenadas
     */
    public void setcoordenadas(String texto) {
	this.coordenadas = texto;
    }
}
