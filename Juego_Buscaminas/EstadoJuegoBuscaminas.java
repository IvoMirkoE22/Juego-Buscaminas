import java.util.Collections;
import java.util.ArrayList;
/**
 * La clase EstadoJuegoBuscaminas representa todo el estado interno del juego Buscaminas.
 * Que celdas hay, si están abiertas,bloqueadas o desbloqueadas, y si el juego termino o no.
 * Toda la lógica del juego esta en esta clase.
 * 
 * @author (Ivo Narváez) 
 * @version (1.0)
 */
public class EstadoJuegoBuscaminas
{
    /**
     * tablero es una matriz de celdas. Cada celda puede tener mina o no 
     * y puede estar abierta,cerrada,bloqueada o desbloqueada.
     */
    private CeldaBuscaminas[][] tablero;
    
    /**
     * Indica si el juego ha terminado
     */
    private boolean juegoTerminado;

    /**
     * Contructor de EstadoJuegoBuscaminas. Crea el tablero principal del juego,que
     * es una matriz de CeldaBuscaminas y después coloca minas en posiciones aleatorias.
     * El número de filas está determinado por Buscaminas.FILAS.
     * El número de columnas está determinado por Buscaminas.COLUMNAS.
     * El número de minas está determinado por Buscaminas.MINAS
     */
    public EstadoJuegoBuscaminas()
    {
        tablero = new CeldaBuscaminas[Buscaminas.FILAS][Buscaminas.COLUMNAS];
        
        /*
         * El tablero es un arreglo bi dimensional de Buscaminas.FILAS filas
         * y Buscaminas.COLUMNAS columnas.
         * Se carga con Buscaminas.FILAS * Buscaminas.COLUMNAS objetos de tipo
         * CeldaBuscaminas diferentes.
         * Dentro del bucle crea cada celda del tablero una por una.Cada CeldaBuscaminas
         * es un objeto, que representa una casilla del tablero, cada celda tiene métodos propios.
         */
         
         for(int i = 0; i < Buscaminas.FILAS; i++){
             for(int j = 0; j < Buscaminas.COLUMNAS; j++){
                 tablero[i][j] = new CeldaBuscaminas();
             }
         }
         
        //llamamos al método colocar minas aleatorias 
        colocarMinasAleatorias(); 
        juegoTerminado = false;
        
    }
    
    /**
     * Coloca minas aleatorias en un tablero vacío.La cantidad de minas está determinada por
     * Buscamias.MINAS.
     * Primero lo que hace es recorrer todo el tablero, y guarda todas las celdas en una lista.
     * Con collections.shuffle(todasLasCeldas) mezcla aleatoriamente la lista.
     * SubList(0, Buscaminas.MINAS) selecciona las primera N celdas de la lista mezclada.
     * A cada una le pone una mina llamando a colocarMina().
     * Como la lista fue mezclada aleatoriamente, esas primeras MINAS celdas serán distintas cada vez que se ejecute.
     */
     private void colocarMinasAleatorias(){
         ArrayList<CeldaBuscaminas> todasLasCeldas = new ArrayList<CeldaBuscaminas>();
         for(int fila = 0; fila < Buscaminas.FILAS; fila++){
             for (int columna = 0; columna < Buscaminas.COLUMNAS; columna++){
                 todasLasCeldas.add(tablero[fila][columna]);
             }
         }
         Collections.shuffle(todasLasCeldas);
         for(CeldaBuscaminas celda : todasLasCeldas.subList(0,Buscaminas.MINAS)){
             celda.colocarMina();
         }
     }
     
     /**
      * Verifica si el juego termino
      * @return true si y solo si el juego termino
      */
     public boolean juegoTerminado(){
         return juegoTerminado;
     }
     
     /**
      * Verifica si la celda en las coordenadas válidas está bloqueada (marcada).
      * @param fila coordenada de fila de la posición a verificar.
      * @param columna coordenada de columna de la posición a verificar.
      * @param true si y solo si la posición (fila,columna) está bloqueada. 
      */
     public boolean estaBloqueada(int fila, int columna){
         return tablero[fila][columna].estaBloqueada();
     }
     
     /**
     * Marca la celda en las coordenadas dadas como bloqueada si el juego no ha terminado.
     * @param fila número de fila donde está ubicada la celda
     * @param columna número de columna donde está ubicada la celda
     */
     public void bloquear(int fila, int columna){
         if(!juegoTerminado){
             tablero[fila][columna].bloquear();
         }
     }
     
     /**
      * Desbloquea la celda en la posición actual si está bloqueada.
      * @param fila número de fila donde está ubicada la celda.
      * @param columna número de columna donde está ubicada la celda
      */
     public void desbloquear(int fila, int columna){
         tablero[fila][columna].desbloquear();
     }
     
     /**
      * Indica si la celda está abierta o no.
      * @param fila número de fila donde está ubicada la celda.
      * @param columna número de columna donde está ubicada la celda.
      * @return true si la celda en la posición dada está abierta.
      */
      public boolean estaAbierta(int fila, int columna){
          return tablero[fila][columna].estaAbierta();
      }
      
      /**
       * Abre una celda en la posición dada. Después de abrirla,si no hay mina en la
       * ubicación, verifica si el número de celdas cerradas coincide con el número
       * de minas, para determinar si el juego terminó. En tal caso, declara
       * el juego terminado llamando a terminarJuego().
       * @param fila número de fila donde está ubicada la celda.
       * @param columna número de columna donde está ubicada la celda.
       */
       public void abrir(int fila, int columna){
           //Este método abre la celda en posición (fila,columna)
       }
}
