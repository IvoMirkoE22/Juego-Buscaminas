
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
    private LectorEntrada lector;
    
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
        
        lector = new LectorEntrada();
        juego = new EstadoJuegoBuscaminas();
        juegoIniciado = false; 
        
    }
    
    /**
     * Iniciar el juego
     */
    public void iniciarJuego(){
        if(juegoIniciado){
            throw new IllegalArgumentException("El juego finalizo.Crea un nuevo juego para comenzar de nuevo"); 
        }
        juegoIniciado = true; 
        
        while(!juego.juegoTerminado()){
            
            System.out.println(juego.toString());
            
            lector.leerEntradaUsuario();
            
            String comando = lector.getComando();
            int fila = lector.getFila();
            int columna = lector.getColumna();
            
            ejecutarComandoUsuario(comando,fila,columna);
            
        }
        
        if(juego.esVictoria()){
            System.out.println("Ganaste");
        }else{
            System.out.println("Perdiste");
        }
        
        juego.abrirTodasLasCeldas();
        System.out.println(juego.toString());
        
        juegoIniciado = false;
    }
    /**
     * Ejecuta un comando del usuario, delegando la acción apropiada al objeto de estado de juego.
     * @param comando es el comando a ejecutar
     * @param fila es la fila de la celda afectada
     * @param columna es la columna de la celda afectada
     */
    public void ejecutarComandoUsuario(String comando,int fila,int columna){
        if(comando.equals(CMD_ABRIR)){
            if(!juego.estaAbierta(fila, columna) && !juego.estaBloqueada(fila,columna)){
                juego.abrir(fila, columna);
            }else{
                System.out.println("No se puede abrir. La celda ya esta abierta o bloqueada.");
            }
        }
        else{
            
            if(comando.equals(CMD_BLOQUEAR)){
                if(!juego.estaAbierta(fila,columna) && !juego.estaBloqueada(fila,columna)){
                    juego.bloquear(fila, columna);
                }
                else{
                    System.out.println("No se puede bloquear.La celda ya esta bloqueada o abierta.");
                }
            }
            else{
                if(juego.estaBloqueada(fila, columna)){
                    juego.desbloquear(fila, columna);
                }
                else {
                    System.out.println("No se puede desbloquear.La celda no esta bloqueada.");
                }
            }
        }
        
    }   
}
