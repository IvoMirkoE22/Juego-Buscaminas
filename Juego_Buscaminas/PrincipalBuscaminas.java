
/**
 * Esta clase es la que arranca todo el programa.
 * Tiene el método main , que es el primer método
 * que se ejecuta cuando se corra el proyecto
 * 
 * @author (Ivo Narváez) 
 * @version (1.0)
 */
public class PrincipalBuscaminas
{
    /**
    * Método main que inciar el juego
    * @param args son los parámetros de linea, no se usa
    * pero se debe poner ya que funciona como la
    * firma estandar del método main en Java
    */
    public static void main(String args[]){
    /**    
    *Crea una instancia de la clase Buscaminas con new Buscaminas()
    */
    Buscaminas buscaminas = new Buscaminas();
    
    /**
    *llamamos al método inciarJuego(), que prepara el tablero,pondra minas
    *y deja el juego listo para empezar.
    */
    buscaminas.inciarJuego();
    
    }
}
