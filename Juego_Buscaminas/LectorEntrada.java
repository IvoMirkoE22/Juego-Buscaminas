import java.util.Scanner;
/**
 * LectorEntada lee y valida los comandos que el usuario escribe por consola
 * para el juego Buscaminas. Devuelve el comando y las coordenadas ya procesados.
 * 
 * Formato de entrada esperada (en una sola línea)
 *     <comando> <fila> <columna>
 * donde comando ∈ {abrir, bloquear, desbloquear]
 *       fila    ∈ [0, Buscaminas.FILAS )
 *       columna ∈ [0, Buscaminas.COLUMNAS)
 *
 * Ejemplo: abrir 3 4
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
     * Los campos se inicializan en valores "nulos" o sentinelas.
     */
    public LectorEntrada()
    {
        lector = new Scanner(System.in); 
        comando = null; 
        coordenadaFila = -1; 
        coordenadaColumna = -1; 
    }

    /**
     * Lee la entrada del usuario desde stdin y guarda el comando y coordenadas en los campos correspondientes.
     * El método continuará leyendo hasta que reciba un comando válido.
     * Lee, valida y almacena un comando del usuario.
     * El método permanece en un bucle hasta que el usuario introduce una línea válida.
     * Tras una lectura exitosa, los campos comando, coordenadaFila y coordenadaColumna
     * quedan listos para usarse mediante sus getters.
     */
    public void leerEntradaUsuario() {
        boolean entradaCorrecta = false;
        do {
            System.out.println(uso(""));
            String[] entrada = lector.nextLine().trim().toLowerCase().split(" ");
                                                                                 
            if (entrada.length != 3) {
                System.out.println(uso("Número incorrecto de argumentos. "));
            }
            else  {
                  if(!esComandoValido(entrada[0])){ 
                      System.out.println(uso("Comando inválido."));
                  }
                  else{
                      if(!esCoordenadaFilaValida(entrada[1]) || !esCoordenadaColumnaValida(entrada[2])){
                          System.out.println(uso("Coordenada(s) inválida(s)."));
                      }
                      else { 
                          entradaCorrecta = true;
                          comando = entrada[0];                        
                          coordenadaFila = Integer.parseInt(entrada[1]);
                          coordenadaColumna = Integer.parseInt(entrada[2]);
                      }
                  }
            }
        } while (!entradaCorrecta); 
        entradaLeida = true; 
    }
    
    /**
     * Verifica si un string es un comando válido.
     * Devuelve true si el string comando coincide con alguna constante de Buscaminas
     * @param comando es el string a verificar
     * @return true si y solo si el parámetro es un comando válido
     */
    private boolean esComandoValido(String comando){
        if(comando == null){
            return false;
        }
        else {
            return comando.equals(Buscaminas.CMD_ABRIR) ||
                   comando.equals(Buscaminas.CMD_BLOQUEAR) ||
                   comando.equals(Buscaminas.CMD_DESBLOQUEAR);
        }
    }
    
    /**
     * Verifica si un string es una coordenada de fila válida.
     * Devuelve true si coordenada representa una fila válida (0 <= fila < FILAS)
     * @param coordenada es el string a verificar
     * @return true si y solo si el parámetro es una coordenada de fila válida
     */
    private boolean esCoordenadaFilaValida(String coordenada){
        if(coordenada == null) {
            return false;
        }
        else{
            try {
                int coord = Integer.parseInt(coordenada);
                return 0 <= coord && coord < Buscaminas.FILAS;
            }
            catch (Exception e) {
                // el string no es un número
                return false;
            }
        }
    }
    
    /**
     * Verifica si un string es una coordenada de columna válida.
     * Devuelve true si coordenada representa una columna válida (0 <= col < COLUMNAS)
     * @param coordenada es el string a verificar
     * @return true si y solo si el parámetro es una coordenada de columna válida
     */
    private boolean esCoordenadaColumnaValida(String coordenada) {
        if(coordenada == null){
            return false;
        }
        else{
            try {
                int coord = Integer.parseInt(coordenada);
                return 0 <= coord && coord < Buscaminas.COLUMNAS;
            }
            catch (Exception e){
                //el string no es un número
                return false;
            }
        }
    }
    
    /**
     * Mejora un mensaje de error con intrucciones de uso.
     * Contruye un mensaje de uso combinando el texto de error con las instrucciones.
     * @param mensajeError es el mensaje de error a mejorar
     * @return un mensaje que incluye el parámetro con instrucciones de uso
     */
    private static String uso(String mensajeError) {
        if (mensajeError == null ){
            throw new IllegalArgumentException("Mensaje de error nulo");
        }
        String res = mensajeError + "\n";
        res += "\"" + Buscaminas.CMD_ABRIR + "\", ";
        res += "\"" + Buscaminas.CMD_BLOQUEAR + "\" o ";
        res += "\"" + Buscaminas.CMD_DESBLOQUEAR + "\" ";
        res += "son comandos aceptados.\n";
        res += "Uso: <comando> <fila> <columna>";
        return res;
    }
    
    /**
     * Obtiene el comando leído
     * Precondición debe llamarse a leerEntradaUsuario() primero.
     * @return el comando leído como string
     */
    public String getComando(){
        if (!entradaLeida) {
            throw new IllegalStateException("Debe llamar a leerEntradaUsuario() antes de obtener el comando");
        }
        return this.comando;
    }
    
    /**
     * Obtiene la coordenada de fila leída.
     * Precondición: debe llamarse a leerEntradaUsuario() primero.
     * @return la coordenada de fila leída
     */
    public int getFila() {
        if(!entradaLeida) {
            throw new IllegalStateException("Debe llamar a leerEntradaUsuario() antes de obtener el comando");
        }
        return this.coordenadaFila;
    }
    
    /**
     * Obtiene la coordenada de fila leída
     * Precondición: debe llamarse a leerEntradaUsuario() primero.
     * @return la coordenada de fila leída
     */
    public int getColumna(){
        if(!entradaLeida){
            throw new IllegalStateException("Debe llamar a leerEntradaUsuario() antes de obtener el comando");
        }
        return this.coordenadaColumna;
    }
}
