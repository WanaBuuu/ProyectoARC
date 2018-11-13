/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.io.Serializable;

public class Paquete implements Serializable{ // Usamos esto por que cuando vamos a enviar a traves de la red y se necesita convertir a byts
    
    private String coordenadas = "";
    private int numCliente, numGrupo, nGrupos, nClientes;
    private boolean ack = false;
    private double timestamp;
    
    public Paquete(String coordenadas, int numCliente, int numGrupo, int nGrupos, int nClientes) { // creo que no lo usamos
	this.coordenadas = coordenadas;
        this.numCliente = numCliente;
        this.numGrupo = numGrupo;
        this.nClientes = nClientes;
        this.nGrupos = nGrupos;
    }
    
    public Paquete(){
        
    }
    
    public double getTimestamp(){
        return this.timestamp;
    }

    public void setTimestamp(double timestamp){
        this.timestamp = timestamp;
    }
    
    public boolean isAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public int getnGrupos() {
        return nGrupos;
    }

    public void setnGrupos(int nGrupos) {
        this.nGrupos = nGrupos;
    }

    public int getnClientes() {
        return nClientes;
    }

    public void setnClientes(int nClientes) {
        this.nClientes = nClientes;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public void setNumCliente(int numCliente) {
        this.numCliente = numCliente;
    }

    public int getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(int numGrupo) {
        this.numGrupo = numGrupo;
    }

    public String getCoordenadas() {
	return coordenadas;
    }

    public void setcoordenadas(String texto) {
	this.coordenadas = texto;
    }
    
}
