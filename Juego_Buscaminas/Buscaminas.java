
/**
 * La clase buscaminas se encargara del estado general de juego
 * y la interaccion con el usuario. Basicamente prepara el tablero,
 * prepara el tablero, pondra minas y deja el juego listo para comenzar. 
 * 
 * @author (Ivo Narváez) 
 * @version (1.0)
 */
public class Buscaminas
{
    /**
     * Comando para abrir una celda
     */
    public static final String CMD_ABRIR = "abrir";
    
    /**
     * Comando para bloquear una celda
     */
    public static final String CMD_BLOQUEAR = "bloquear";
    
    /**
     * Comando para desbloquear una celda
     */
    public static final String CMD_DESBLOQUEAR = "desbloquear";
    
    /**
     * Número de filas permitido en el juego
     */
    public static final int FILAS = 9;
    
    /**
     * Número de columnas permitido en el juego
     */
    public static final int COLUMNAS = 9;
    
    /**
     * Número de minas permitido en el juego
     */
    public static final int MINAS = 10;
    
    /**
     * Lector de entrada para procesar comando del usuario
     */
    private LectorEntada lector;
    
    /**
     * Objeto que representa el estado interno del juego Buscaminas
     */
    private EstadoJuegoBuscaminas juego;
    
    /**
     * Verifica si el juego comenzo o no
     * ¿El juego ha comenzado?
     */
    private boolean juegoIniciado ;
    
    /**
     * Contructor de Buscaminas. Lanza un juego buscaminas con un tablero
     * de 9 filas por 9 columnas, con 10 minas.Dentro del bucle se muestra el
     * tablero, se le pide al jugador una acción, se interpretan los datos
     * ingresados, y luego se ejecuta el comando sobre la celda correspondiente.
     */
    public Buscaminas(){
        
        lector = new LectorEntrada();//crea el lector de entrada
        juego = new EstadoJuegoBuscaminas();//Inicializa el estado del juego
        
        juegoIniciado = false; //Juego no iniciado
        
    }
    
    /**
     * Iniciar el juego
     */
    public void iniciarJuego(){
        if(juegoIniciado){
            throw new IllegalArgumentException("El juego finalizo.Crea un nuevo juego para comenzar de nuevo"); 
        }
        juegoIniciado = true; //Juego iniciado
        
        while(!juego.juegoTerminado()){
            /*
             * muestra por consola el estado actual del tablero,
             * con celdad ya abiertas, cerradas o bloqueadas
             */
            System.out.println(juego.toString());
            /*
             *Espera a que el jugador escriba un comando válido, como
             *abrir, bloquear o desbloquear y las coordenadas fila y
             *columna de la celd que se quiere afectar
             */
            lector.leerEntradaUsuario();
            /*
             * Guarda lo que el jugador escribio para poder procesarlo
             */
            String comando = lector.getComando();
            int fila = lector.getFila();
            int columna = lector.getColumna();
            /*
             * Ejecuta la acción que pidio el usuario sobre la celda indicada
             */
            ejecutarComandoUsuario(comando,fila,columna);
            
        }
        /*
         * Cuando el bucle o juego termina se evalua si el jugador gano o perdio
         */
        if(juego.esVictoria()){
            System.out.println("Ganaste");
        }else{
            System.out.println("Perdiste");
        }
        /*
         * Abre todas as las celdas del tablero, para mostrar que hay en
         * cada celda(por ejemplo las minas) y lo imprime
         */
        juego.abrirTodasLasCeldas();
        System.out.println(juego.toString());
        /*
         * Marca el juego como ya terminado, para no poder jugar otra vez con el mismo objeto
         */
        juegoIniciado = false;
    }
    /**
     * Ejecuta un comando del usuario, delegando la acción apropiada al objeto de estado de juego.
     * @param comando es el comando a ejecutar
     * @param fila es la fila de la celda afectada
     * @param columna es la columna de la celda afectada
     */
    public void ejecutarComandoUsuario(String comando,int fila,int columna){
        //-----------ABRIR-----------
        if(comando.equals(CMD_ABRIR)){
            //solo se puede abrir si la celda no está abierta ni bloqueada.
            if(!juego.estaAbierta(fila, columna) && !juego.estaBloqueada(fila,columna)){
                juego.abrir(fila, columna);
            }else{
                System.out.println("No se puede abrir. La celda ya esta abierta o bloqueada.");
            }
        }
        else{
            //-------BLOQUEAR-------
            if(comando.equals(CMD_BLOQUEAR)){
                //solo se puede bloquear si la celda esta cerrada y sin bloqueo.
                if(!juego.estaAbierta(fila,columna) && !juego.estaBloqueada(fila,columna)){
                    juego.bloquear(fila, columna);
                }
                else{
                    System.out.println("No se puede bloquear.La celda ya esta bloqueada o abierta.");
                }
            }
            else{
                //---------DESBLOQUEAR----------
                if(juego.estaBloqueada(fila, columna)){
                    //Solo se puede desbloquear si la celda esta bloqueada.
                    juego.desbloquear(fila, columna);
                }
                else {
                    System.out.println("No se puede desbloquear.La celda no esta bloqueada.");
                }
            }
        }
        
        //-----------COMANDO DESCONOCIDO------------
        System.out.println("Comando desconocido: "+ comando);
    }   
}
