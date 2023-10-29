import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class Cuenta {

	// Declaraci�n de atributos
		double saldo;
		Usuario usuario;
		//implementamos las listas como ArrayList
		ArrayList<Gasto> gastos;
		ArrayList<Ingreso> ingresos;
	
	// Constructor �nico
	public Cuenta(Usuario usuario) {
		// los nuevos usuarios se crean con saldo 0�
		saldo = 0;
		this.usuario = usuario;
		// instanciamos las listas de gastos e ingresos
		gastos = new ArrayList<>();
		ingresos = new ArrayList<>();
	}

	// Getters y setters
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public double addIngresos (String description, double cantidad) {
		/* Instanciamos un nuevo objeto Ingreso como par�metro para
		 * a�adirlo a la lista ingresos */ 
		ingresos.add(new Ingreso(cantidad, description));
		// Sumamos el ingreso al saldo de la cuenta
		saldo += cantidad;
		return saldo;
	}

	// El m�todo addGastos puede lanzar una excepci�n de tipo GastoException
	public double addGastos (String description, double cantidad) throws GastoException {
		/* Comprobamos que se disponga de saldo antes de realizar
		 * la operaci�n */
		if (saldo - cantidad < 0) {
			throw new GastoException();
		} else {
			/* Instanciamos un nuevo objeto Gasto como par�metro para
			 * a�adirlo a la lista gastos */
			gastos.add(new Gasto(cantidad, description));
			// Restamos el gasto al saldo de la cuenta
			saldo -= cantidad;
		}
		return saldo;
	}
	
	public ArrayList<Ingreso> getIngresos() {
		return ingresos;
	}

	public ArrayList<Gasto> getGastos() {
		return gastos;
	}

	// Sobreescribimos el m�todo toString()
	@Override
	public String toString() {
		//return "Cuenta [saldo=" + saldo + ", usuario=" + usuario + ", gastos=" + gastos + ", ingresos=" + ingresos + "]";
		return "El saldo del usuario \"" + usuario.nombre + "\" es de: " + Main.toDoubleES(saldo) + "�";
	}
	
	




	
}
