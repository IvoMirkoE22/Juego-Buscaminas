
/**
 * CeldaBuscaminas representa las celdas de un juego Buscaminas. Una celda puede conteenr una mina
 * o estar vacía.Puede estar abierta o cerrada (al abrirla se revela su contenido, y si contiene
 * una mina, finaliza el juego). Una celda puede bloquearse para evitar abrirla accidentalmente.
 * 
 * @author (Ivo Narváez) 
 * @version (1.0)
 */
public class CeldaBuscaminas
{
    /**
     * ¿Está la celda bloqueada? Una celda bloqueada está cerrada y no puede abrirse
     */
    private boolean estaBloqueada;
    
    /**
     * ¿Contiene la celda una mina? Al abrir una celda con mina, se termina el juego
     */
    private boolean tieneMina;
    
    /**
     * ¿Está la celda abierta?
     */
    private boolean estaAbierta;
    
    /**
     * Contructor de CeldaBuscaminas.
     * Crea una celda que está cerrada, desbloqueada y sin minas.
     */
    public CeldaBuscaminas()
    {
        
        this.estaBloqueada = false;
        this.tieneMina = false;
        this.estaAbierta = false; 
    }
    
    

  
}
