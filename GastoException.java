/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class GastoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// si se genera la excepci�n simplemente devolvemos un mensaje de error
	public GastoException() {
		super("Saldo insuficiente.\n");
	}
}
