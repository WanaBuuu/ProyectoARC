package Clt;

/**
 * @brief Clase cliente
 * @author G 1.1
 */
public class CltMulti {

    /**
     * @brief Método principal de la clase. Arranca la vista
     * @param args Argumentos
     */
    public static void main(String[] args) {
	
        PantallaCliente  pantalla= new PantallaCliente();
        pantalla.setVisible(true);
    }
    
    /**
     * @brief Metodo de lanzar cliente
     * @param nVecinos Numero de vecinos
     * @param nClientesTotales Numero de clientes totales
     * @param ciclos Numero de ciclos que desea hacer el sistema
     * @param ip IP a la que va dirigida - servidor -
     * @param port Puerto al que va dirigido
     */
    public void StartCliente(int nVecinos, int nClientesTotales, int ciclos, String ip, int port){
        int nClientes = (nVecinos + 1);
        int nGrupos = (nClientesTotales / (nVecinos +1));
	int pausa = 10000;
	int it = 0;
        String s;
     
        do {
            for(int i = 0; i < nGrupos; i++){
                ThreadGroup grupo = new ThreadGroup(String.valueOf(i));
                for(int j = 0; j < nClientes; j++){
                    Cliente c = new Cliente(ip,port,pausa,i,j, nGrupos, nClientes);

                    new Thread(c).start(); // crea hilo que este dentro del grupo x con nombre j y lo empiezas
                    System.out.println("Grupo del hilo: " + grupo.getName() + " Numero del hilo: " + String.valueOf(j));
                }
            }
            
            it++;
        }while (it < ciclos);
        
    }
}
