/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clt;

/**
 *
 */
public class CltMulti {

    public static void main(String[] args) {
	int nClientes = 3;
        int nGrupos = 2;
	int pausa = 10000;
	String ip = "localhost";
	int port = 10000;
        String s;
       
        for(int i = 0; i < nGrupos; i++)
        {
            ThreadGroup grupo = new ThreadGroup(String.valueOf(i));
            for(int j = 0; j < nClientes; j++){
                Cliente c = new Cliente(ip,port,pausa,i,j, nGrupos, nClientes);
                
                new Thread(c).start(); // crea hilo que este dentro del grupo x con nombre j y lo empiezas
                System.out.println("Grupo del hilo: " + grupo.getName() + " Numero del hilo: " + String.valueOf(j));
            }
        }
    }
}