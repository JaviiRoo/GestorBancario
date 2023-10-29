/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class Gasto extends Dinero {

	// Constructor �nico
	public Gasto(double gasto, String description) {
		// se inicializan los atributos heredados de Dinero
		super.dinero = gasto;
		super.description = description;		
	}

	// Sobreescribimos el m�todo toString() indicando los datos del gasto introducido.
	@Override
	public String toString() {
		return "Gasto en concepto de: \"" + super.description + "\", por importe de: " 
				+ Main.toDoubleES(dinero) + "�";
	}

}
