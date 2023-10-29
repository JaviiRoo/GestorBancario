/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public abstract class Dinero {

	// Declaración de atributos
	protected double dinero;
	protected String description;
	
	// Getters y setters
	public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
