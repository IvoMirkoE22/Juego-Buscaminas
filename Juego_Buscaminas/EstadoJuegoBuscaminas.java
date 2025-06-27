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
           /*
            * Primero verificamos, si la coordenada de fila y columna resulta no ser válida
            * se lanza una ecepción que estaa fuera de rango..
            */
           if(!esCoordenadaValida(fila, columna)){
               throw new IllegalArgumentException("Esta fuera de rango");
           }
           //Crea una variable llamada matriz que representa la celda que queremos abrir
           CeldaBuscaminas matriz = tablero[fila][columna];
           //Solo se puede abrir una celda cerrada y no bloqueada
           //Si ya está abierta o tiene bandera(bloqueada), no se puede abrir
           if(matriz.estaAbierta() || matriz.estaBloqueada()){
               throw new IllegalArgumentException("La celda debe estar cerrada y no bloqueada");
           }
           
           matriz.abrir();
           //Si esa celda tiene una mina,perdes.Se termina el juego.
           if(matriz.tieneMina()){
               terminarJuego();
           }
           /*
            * Si la celda abierta no tiene minas alrededor, entonces
            * Se activa el efecto cascada, abre todas las celdas vecinas que también
            * estén cerradas y no tengan minas
            */
           if(contarMinasVecinas(fila,columna) == 0){
               for(int i = fila-1; i <= fila+1;i++){
                   for(int j = columna - 1; j <= columna+1; j++){
                       if(esCoordenadaValida(i,j)){
                           if(tablero[i][j].estaCerrada() && !tablero[i][j].tieneMina() && !tablero[i][j].estaBloqueada()){
                               abrir(i,j);
                           }
                       }
                   }
               }
           }
           /*
            * Si después de abrir la celda (y potencialmente del efecto cascada)solo queda cerradas
            * las celdas con minas (es decir, no quedan celdas sin minas cerradas), el juego también termina.
            * Verifica si ya se abrieron todas las celdas que no tienen minas.
            * Si solo quedan cerradas las que tienen minas, significa que ganaste, y el juego termina automaticamente.
            */
            if(contarCeldasCerradas() == Buscaminas.MINAS){
                terminarJuego();
            }
           
       }
       /**
        * Finaliza el juego
        */
       public void terminarJuego() {
           juegoTerminado = true;
       }
       
       /**
        * Indica si el jugador ganó. El juego debe haber terminado.
        * @return true si las celdas cerradas son iguales a la cantidad de minas
        * @throws IllegalSatateException si el juego no ha terminado
        */
        public boolean esVictoria(){
            if (!juegoTerminado) throw new IllegalStateException("El juego no ha terminado");
            return contarCeldasCerradas() == Buscaminas.MINAS;
        }
       
        /**
         * Cuenta el número de minas en las celdas vecinas a la celda en posición (fila,columna)
         * @param fila coordenada de fila de la celda a verificar
         * @param columna coordenada de columna de la celda a verificar
         * @return número de celdas vecinas que contienen minas. El resultado esta ente 0 y 8.
         */
        public int contarMinasVecinas(int fila, int columna){
            
            int suma=0;
            for(int i = fila -1; i <= fila+1;i++){
                for(int j = columna-1; j<=columna+1; j++){
                    if(esCoordenadaValida(i,j) && !(i == fila && j == columna)){
                        if(tablero[i][j].tieneMina()){
                            suma++;
                        }
                    }
                }
            }
            return suma;
        }
        
        /**
         * Abre todas las celdas cerradas en el tablero,independientemente de si tienen minas
         * o están bloqueadas. Las celdas bloqueadas deben desbloquearse antes de abrirlas.
         */
        public void abrirTodasLasCeldas() {
            for(int i=0; i < Buscaminas.FILAS; i++){
                for(int j=0; j < Buscaminas.COLUMNAS; j++){
                    if(tablero[i][j].estaCerrada()){
                        if(tablero[i][j].estaBloqueada()){
                            tablero[i][j].desbloquear();
                        }
                        tablero[i][j].abrir();
                    }
                }
            }
        }
        
        /**
         * Verifica si las coordenadas son válidas.
         * @param fila número de fila a verififcar
         * @param columna número de columna a verificar
         * @return true si las coordenadas están dentro del rango válido del tablero
         */
        public static boolean esCoordenadaValida(int fila, int columna){
            return fila >= 0 && fila < Buscaminas.FILAS && columna >= 0 && columna < Buscaminas.COLUMNAS;
        }
        
        /**
         * Calcula el número de celdas cerradas en el tablero.
         * @return número de celdas cerradas en el tablero.
         */
        public int contarCeldasCerradas(){
            int suma= 0;
            for(int i=0; i < Buscaminas.FILAS; i++){
                for(int j=0; j < Buscaminas.COLUMNAS; j++){
                    if(tablero[i][j].estaCerrada()){
                        suma++;
                    }
                }
            }
            return suma;
        }
        
        /**
         * Proporciona una representación textual del estado del juego.
         * @return String que representa visualmente el estado del juego.
         */
        public String toString() {
            StringBuilder resultado = new StringBuilder();
            
            if(juegoTerminado){
                resultado.append("Juego Terminado.\n");
            } else {
                resultado.append("Juego en Progreso.\n");
            }
            
            resultado.append("   0  1  2  3  4  5  6  7  8  \n");
            resultado.append("   --------------------------\n");
            
            for (int fila = 0; fila < Buscaminas.FILAS; fila++){
                resultado.append(fila).append("|");
                for (int columna = 0; columna < Buscaminas.COLUMNAS; columna++){
                    CeldaBuscaminas celdaActual = tablero[fila][columna];
                    
                    if(celdaActual.estaAbierta()){
                        if(celdaActual.tieneMina()) {
                            resultado.append(" X ");
                        } else {
                            resultado.append(" ").append(this.contarMinasVecinas(fila,columna)).append(" ");
                        }
                    } else {
                        if(celdaActual.estaBloqueada()) {
                            resultado.append(" B ");
                        } else {
                            resultado.append(" - ");
                        }
                    }
                } 
                resultado.append("|").append(fila).append("\n");
            }
            
            resultado.append("   --------------------------\n");
            resultado.append("   0  1  2  3  4  5  6  7  8 \n");

            return resultado.toString();
        }       
    }
