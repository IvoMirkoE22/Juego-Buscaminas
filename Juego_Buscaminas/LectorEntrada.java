
import java.util.Scanner;
/**
 * LectorEntada lee los comando del buscaminas ingresados por el usuario a través de entrada estándar.
 * LectorEntrada es responsable de validar los comandos y devolverlos ya procesados.
 * 
 * @author (Ivo Narváez) 
 * @version (1.0)
 */
public class LectorEntrada
{
    /**
     * Scanner para leer la entrada
     */
    private Scanner lector;
    
    /**
     * Almacena el comando ingresado por el usuario
     */
    private String comando;
    
    /**
     * Almacena la coordenada de fila ingresada por el usuario
     */
    private int coordenadaFila;
    
    /**
     * Almacena la coordenada de columna ingresada por el usuario
     */
    private int coordenadaColumna;
    
    /**
     * Indica si la entrada ya fue leida (evita recuperar resultados antes de leer).
     */
    private boolean entradaLeida;
    
    /**
     * Crea un nuevo LectorEntrada que lee texto de la entrada estándar.
     */
    public LectorEntrada()
    {
        //System.in se usa para leer desde el teclado
        lector = new Scanner(System.in);
        comando = null; //no hay nada
        coordenadaFila = -1; //fila ingresada
        coordenadaColumna = -1; //columna ingresada
    }

    
}
