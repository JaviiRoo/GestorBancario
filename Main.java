import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author Javier Rodriguez Sanchez
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Declaración de variables
		Scanner sc = new Scanner(System.in);
		Usuario usuario;
		boolean DNIcorrecto;
		Cuenta cuenta;
		int opcion = -1; // inicializamos a -1 ya que el valor por defecto es la opción de salida (0)
		String descripcion;
		double cantidad;
		
		/* 
		 * 1. Creación del usuario y sus datos 
		 */
		//Introducción de los datos de usuario
		// para almacenarlos, utilizamos los getter disponibles de la clase Usuario
		usuario = new Usuario();
		System.out.println("Hola, bienvenido a tu aplicación de gestión de gastos personales");
		System.out.println("Por favor, introduce tu nombre de usuario (sin espacios):");
		/* al usar sc.next() limitamos la captura a solo una palabra, es una restricción que no está
		 * especificada en el enunciado pero que se basa en los ejemplos mostrados. En caso de
		 * introducir más de una palabra, el resto se pierde. */
		usuario.setNombre(sc.next());
		sc.nextLine();
		System.out.println("Introduce tu edad:");
		usuario.setEdad(sc.nextInt());
		sc.nextLine();
		// solicitamos el DNI hasta que se introduzca uno con el formato correcto
		do {
			System.out.println("Introduce tu DNI (formatos admitidos \"12345678A\" o \"12345678-A\"):");
			DNIcorrecto = usuario.setDNI(sc.nextLine());
		} while (!DNIcorrecto);
		System.out.println("Se ha creado el usuario \"" + usuario.getNombre() + "\" correctamente.");
		System.out.println();

		
		/*
		 * 2. Creación de la cuenta
		 */
		cuenta = new Cuenta(usuario);
		
		/*
		 * 3. Visualización del menú
		 */
		// Como el menú debe mostrarse después de cada operación, lo implementamos dentro de un bucle
		while (opcion != 0) {
			System.out.println("Realiza una nueva acción \n"
							+ "1 Introduce un nuevo gasto \n"
							+ "2 Introduce un nuevo ingreso \n"
							+ "3 Mostrar gastos \n"
							+ "4 Mostrar ingresos \n"
							+ "5 Mostrar saldo \n"
							+ "0 Salir \n");
			opcion = sc.nextInt();
			sc.nextLine();
			/*
			 * 4. Cada acción realiza una operación
			 */
			switch (opcion) {
				case 1: {
					System.out.println("Por favor, introduce la descripción del gasto");
					descripcion = sc.nextLine();
					// vamos a controlar que la cantidad introducida siga el formato europeo
					cantidad = compruebaCantidad();
					// añadimos el gasto a la cuenta, que nos devuelve el saldo restante
					// este método puede generar una excepción, por lo que la tenemos que gestionar
					try {
						System.out.println("Saldo restante: " + toDoubleES(cuenta.addGastos(descripcion, cantidad)) + "€");
						System.out.println();
					} catch(GastoException error) {
						// en caso de producirse, mostramos el mensaje de error
						System.out.println(error.getMessage());
					}
					break;
				}
				case 2: {
					System.out.println("Por favor, introduce la descripción del ingreso");
					descripcion = sc.nextLine();
					// vamos a controlar que la cantidad introducida siga el formato europeo
					cantidad = compruebaCantidad();
					// añadimos el ingreso a la cuenta, que nos devuelve el saldo restante
					System.out.println("Saldo restante: " + toDoubleES(cuenta.addIngresos(descripcion, cantidad)) + "€");
					System.out.println();
					break;
				}
				case 3: {
					// comprobamos si hay gastos para mostrar
					if (cuenta.getGastos().isEmpty()) {
						System.out.println("No hay gastos para mostrar.\n");
					} else {
						// hacemos uso de un for mejorado para recorrer la lista de gastos
						for (Gasto gasto : cuenta.getGastos()) {
							System.out.println(gasto.toString());
						}
						System.out.println();
					}
					break;
				}
				case 4: {
					// este caso es similar al anterior pero con la lista de ingresos
					if (cuenta.getIngresos().isEmpty()) {
						System.out.println("No hay ingresos para mostrar.\n");
					} else {
						for (Ingreso ingreso : cuenta.getIngresos()) {
							System.out.println(ingreso.toString());
						}
						System.out.println();
					}
					break;
				}
				case 5: {
					// mostramos el saldo
					System.out.println("El saldo actual de la cuenta es de " + toDoubleES(cuenta.getSaldo()) + "€\n");
					break;
				}
				case 0: {
					// mostramos el fin de programa
					System.out.println("Fin del programa.\nGracias por utilizar la aplicación.");
					// antes de salir, cerramos el objeto Scanner para liberar la memoria
					sc.close();
					break;
				}
			}
		}
	}

	/**
	 * @param 
	 */
	/*
	 * compruebaCantidad es un pequeño método implementado para reutilizar el código en los casos 
	 * de las opciones 1 y 2 del menú, donde se pide introducir una cantidad que puede contener decimales.
	 * Mediante un try .. catch manejamos la excepción del tipo InputMismatchException en el caso
	 * de que se introduzca un valor incompatible con un double con coma decimal.
	 */
	private static double compruebaCantidad() {
		boolean cantidadCorrecta = false;
		double valor = 0;
		Scanner lectura = new Scanner(System.in);
		
		do {
			System.out.println("Introduce la cantidad");
			try {
				valor = lectura.nextDouble();
				cantidadCorrecta = true;
			} catch (InputMismatchException errorTipo) {
				cantidadCorrecta = false;
				lectura.nextLine();
				System.out.println("Las cantidades deben ir en formato europeo ####,##");
			}
		} while (!cantidadCorrecta);
		lectura.nextLine();
		return valor;
	}

	/**
	 * @param valor Valor numérico tipo double
	 */
	/* 
	 * Método estático que instancia un objeto NumberFormat para obtener un string que
	 *  representa valores tipo double en formato de España 
	 */
	public static String toDoubleES (double valor) {
		Locale spanish = new Locale("es", "ES");
		NumberFormat nf = NumberFormat.getInstance(spanish);
		return nf.format(valor);
	}
	
}
