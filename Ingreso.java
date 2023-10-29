/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class Ingreso extends Dinero {

	// Constructor único
	public Ingreso(double ingreso, String description) {
		// se inicializan los atributos heredados de Dinero
		super.dinero = ingreso;
		super.description = description;
	}

	// Sobreescribimos el método toString() indicando los datos del ingreso introducido.
	@Override
	public String toString() {
		return "Ingreso en concepto de: \"" + super.description + "\", por importe de: " 
				+ Main.toDoubleES(dinero) + "€";
	}

}
