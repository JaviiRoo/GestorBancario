/* se importan las clases Matcher y Pattern del paquete java.util.regex
 * para la comprobaci�n del DNI mediante expresiones regulares
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class Usuario {

	// Declaraci�n de atributos
	String nombre;
	int edad;
	String DNI;
	
	/* Dejamos vac�o el constructor por defecto ya que los atributos
	 * se tendr�n que establecer mediante los setter */
	public Usuario() {}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getDNI() {
		return DNI;
	}

	public boolean setDNI(String DNI) {
		// declaramos un objeto Pattern para definir la expresi�n regular
		Pattern patron = Pattern.compile("^[0-9]{8}-?[a-zA-Z]{1}$");
		// declaramos un objeto Matcher para comprobar si DNI cumple el patr�n
		Matcher DNICorrecto = patron.matcher(DNI);
		if (DNICorrecto.matches()) {
			this.DNI = DNI;
			return true;
		} else {
			System.out.println("El DNI introducido es incorrecto.");
			return false;
		}
	}

	// Sobreescribimos el m�todo toString()
	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", edad=" + edad + ", DNI=" + DNI + "]";
	}
	

	

}
