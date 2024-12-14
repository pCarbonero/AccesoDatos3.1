package clases;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			ConexionDB.useDATABASE();
			menu();
			System.out.println("Adios");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Funcion que muestra el menu con las posibles opciones
	static void menu() throws SQLException {
		int opc = -1;

		while (opc != 0) {
			System.out.println("1. Crear tablas ");
			System.out.println("2. Insertar datos en las tablas ");
			System.out.println("3. Listado de datos de las tablas ");
			System.out.println("4. Modificar datos de las tablas ");
			System.out.println("5. Eliminar datos de las tablas ");
			System.out.println("6. Borrar tablas ");
			System.out.println("0. Salir ");
			opc = sc.nextInt(); // aqui ocurre la excepcion (solo despues del metodo modificar que te pase
			sc.nextLine();
			
			switch (opc) {
			case 1: {
				crearTablas();
				break;
			}
			case 2: {
				insertarDatos();
				break;
			}
			case 3:{
				listarElementos();
				break;
			}
			case 4:{
				modificarElementos();
				break;
			}
			case 5:{
				eliminarDatos();
				break;
			}
			case 6:{
				borrarTabla();
				break;
			}

			}// switch
		}// while
	}// menu

	// FUncion que muestra las opciones de creacion de tablas
	static void crearTablas() throws SQLException {
		int opc = 0;

		System.out.println("1. Crear todas las tablas ");
		System.out.println("2. Crear tabla Mesa ");
		System.out.println("3. Crear tabla Productos ");
		System.out.println("4. Crear tabla Factura ");
		System.out.println("5. Crear tabla Pedido ");
		System.out.println("0. Volver atrás");
		opc = sc.nextInt();
		sc.nextLine();	
		
		switch (opc) {
		case 1: {
			MetodosDataBase.crearTablaMesa();
			MetodosDataBase.crearTablaProductos();
			MetodosDataBase.crearTablaFactura();
			MetodosDataBase.crearTablaPedido();
			break;
		}	
		case 2:{
			MetodosDataBase.crearTablaMesa();
			break;
		}
		case 3:{
			MetodosDataBase.crearTablaProductos();
			break;
		}
		case 4:{
			if(MetodosDataBase.crearTablaFactura()) {
				System.out.println("Tabla creada correctamente");
			}
			else {
				System.out.println("Necesitas crear la tabla Mesa");
			}
			break;
		}
		case 5:{
			if(MetodosDataBase.crearTablaPedido()) {
				System.out.println("Tabla creada correctamente");
			}
			else {
				System.out.println("Necesitas crear las tablas Factura y Productos");
			}
			break;
		}

		}// switch
	}// crear tablas

	static void insertarDatos() throws SQLException {
		int opc = -1;

			System.out.println("1. Insertar en todas las tablas ");
			System.out.println("2. Insertar en la tabla Mesa ");
			System.out.println("3. Insertar en la tabla Productos ");
			System.out.println("4. Insertar en la tabla Factura ");
			System.out.println("5. Insertar en la tabla Pedido ");
			System.out.println("0. Volver atrás");
			opc = sc.nextInt();
			sc.nextLine();
			
			switch (opc) {
			case 1: {
				MetodosDataBase.insertarElementos("Mesa");
				MetodosDataBase.insertarElementos("Productos");
				MetodosDataBase.insertarElementos("Factura");
				MetodosDataBase.insertarElementos("Pedido");
				break;
			}	
			case 2:{
				MetodosDataBase.insertarElementos("Mesa");
				break;
			}
			case 3:{
				MetodosDataBase.insertarElementos("Productos");
				break;
			}
			case 4:{
				if(MetodosDataBase.insertarElementos("Factura")) {
					System.out.println("Tabla creada correctamente");
				}
				else {
					System.out.println("Necesitas crear la tabla Mesa");
				}
				break;
			}
			case 5:{
				if(MetodosDataBase.insertarElementos("Pedido")) {
					System.out.println("Tabla creada correctamente");
				}
				else {
					System.out.println("Necesitas crear las tablas Factura y Productos");
				}
				break;
			}
			}
	}// insertar
	
	// funcion que se encarga de preguntar al usuario por la consulta que quiere realizar
	static void listarElementos() throws SQLException {
		int opc;
		System.out.println("1. Tabla Mesa ");
		System.out.println("2. Tabla Productos ");
		System.out.println("3. Tabla Factura ");
		System.out.println("4. Tabla Pedido ");
		System.out.println("0. Salir ");
		System.out.println("¿De que tabla quieres realizar la consulta? ");
		opc = sc.nextInt();
		sc.nextLine();
		
		switch (opc) {
		case 1: {
			selectMesa(false);
			break;
		}
		case 2:{
			selectProductos(false);
			break;
		}
		case 3:{
			selectFactura(false);
			break;
		}
		case 4:
			selectPedido(false);
			break;
		default:
			System.out.println("Opción no válida");
		}		
	}// funcion inserts

	static void modificarElementos() {
		int opc = 0;
		
		System.out.println("1. Tabla Mesa");
		System.out.println("2. Tabla Productos");
		System.out.println("3. Tabla Factura");
		System.out.println("4. Tabla Pedido");
		System.out.println("Elige sobre que tabla quieres realizar una modificacion ");
		opc = sc.nextInt();
		sc.nextLine();
		
		switch (opc) {
		case 1: {
			modificarTabla("Mesa");
			break;
		}
		case 2: {
			modificarTabla("Productos");
			break;
		}
		case 3: {
			modificarTabla("Factura");
			break;
		}
		case 4: {
			modificarTabla("Pedido");
			break;
		}
		default:
			System.out.println("Opción no válida");
		}
	}
	
	

	
	// funcines para realizar los select y eliminar datos en la base de datos
	private static void selectMesa(boolean isDelete) {
		int opcMesa;
		String comparador;
		System.out.println("1. Todas las filas");
		System.out.println("2. Por idMesa ");
		System.out.println("3. Por número de comensales");
		System.out.println("4. Por reserva ");
		System.out.println("Elige la columna para filtrar los datos ");
		opcMesa = sc.nextInt();
		// se hace otro nextLine para vaciar el buffer del escaner para que no haya errores
		// se hará durante todo el programa
		sc.nextLine();
		try {
			switch (opcMesa) {
			case 1:{
				ejecutarOperacion(isDelete, "Mesa", null, null, null);
				break;
			}
			case 2: {
				System.out.println("Inserta el id de la mesa ");
				String idMesa = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (!comprobarComparador(comparador, "LIKE")) {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				else {
					ejecutarOperacion(isDelete, "Mesa", "idMesa", idMesa, comparador);			
				}
				break;
			}
			case 3:{
				System.out.println("Inserta el número de comensales ");
				String numComensales = sc.nextLine();
				comparador = getComparadorDeseado();
				if (comparador.equals("LIKE")) {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				else {
					ejecutarOperacion(isDelete, "Mesa", "numComensales", numComensales, comparador);
				}
				
				break;
			}
			case 4:{
				System.out.println("Buscar por mesas con reserva? S/N");
				String siNo = sc.next();
				sc.nextLine();
				String reserva = (siNo.equalsIgnoreCase("s"))? "1" : "0";
				
				ejecutarOperacion(isDelete, "Mesa", "reserva", reserva, "=");						
				break;
			}	
			}// switch Mesa
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL " + sqlE.getMessage());
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}
	
	private static void selectProductos(boolean isDelete) throws SQLException{
		int opcProductos;
		String comparador;
		System.out.println("1. Todas las filas");
		System.out.println("2. Por idProduco ");
		System.out.println("3. Por el nombre del producto");
		System.out.println("4. Por el precio del producto");
		System.out.println("Elige la columna para filtrar los datos ");
		opcProductos = sc.nextInt();
		sc.nextLine();
		try {
			switch (opcProductos) {
			case 1:{
				// se llama al metodo que lista las mesas con los parametros con valores predetrmindos
				ejecutarOperacion(isDelete, "Productos", null, null, null);
				break;
			}
			case 2: {
				System.out.println("Inserta el id del producto ");
				String idProducto = sc.nextLine();			
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Productos", "idProducto", idProducto, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				
				break;
			}
			case 3:{
				System.out.println("Inserta el nombre del producto ");
				String denominacion = sc.nextLine();
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "<") && comprobarComparador(comparador, ">")) {
					ejecutarOperacion(isDelete, "Productos", "Denominacion", denominacion, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}		
				break;
			}
			case 4:{
				System.out.println("Inserta el precio del producto");
				String precio = sc.nextLine();
				comparador = getComparadorDeseado();

				MetodosDataBase.listadoProductos("Precio", precio, comparador);
				break;
			}		
			}// switch Productos
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL ");
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}// selectProductos

	private static void selectFactura(boolean isDelete) throws SQLException{
		int opcFactura;
		String comparador;
		System.out.println("1. Todas las filas ");
		System.out.println("2. Por id de la Factura ");
		System.out.println("3. Por id de la Mesa ");
		System.out.println("4. Por tipo de pago ");
		System.out.println("5. Por el importe ");
		System.out.println("Elige la columna para filtrar los datos ");
		opcFactura = sc.nextInt();
		sc.nextLine();
		try {
			switch (opcFactura) {
			case 1:{
				// se llama al metodo que lista las mesas con los parametros con valores predetrmindos
				ejecutarOperacion(isDelete, "Mesa", null, null, null);
				break;
			}
			case 2: {
				System.out.println("Inserta el id de la factura ");
				String idFactura = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Factura", "idFactura", idFactura, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}

				break;
			}
			case 3:{
				System.out.println("Inserta el nombre del producto ");
				String idMesa = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Factura", "idMesa", idMesa, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}

				break;
			}
			case 4:{
				int opcPago = 0;
				String tipoPago;
				System.out.println("1. Efectivo ");
				System.out.println("2. Tarjeta ");
				System.out.println("Elige el tipo de pago");
				opcPago = sc.nextInt();
				sc.nextLine();
				
				if (opcPago == 1) {
					tipoPago = "efectivo";
				}else {
					tipoPago = "tarjeta";
				}
				
				ejecutarOperacion(isDelete, "Factura", "tipoPago", tipoPago, "=");
				break;
			}	
			case 5:{
				System.out.println("Inserta el importe");
				String importe = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Factura", "Importe", importe, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				break;
			}
			}// switch Facturas
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL ");
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}// selectFacturas
	
	private static void selectPedido(boolean isDelete) throws SQLException{
		int opcPedido;
		String comparador;
		System.out.println("1. Todas las filas ");
		System.out.println("2. Por id del pedido ");
		System.out.println("3. Por id de la Factura ");
		System.out.println("4. Por id del producto ");
		System.out.println("5. Por la cantidad ");
		System.out.println("Elige la columna para filtrar los datos ");
		opcPedido = sc.nextInt();
		sc.nextLine();
		
		try {
			switch (opcPedido) {
			case 1:{
				// se llama al metodo que lista las mesas con los parametros con valores predetrmindos
				ejecutarOperacion(isDelete, "Pedido", null, null, null);

				break;
			}
			case 2:{
				System.out.println("Inserta el id del pedido ");
				String idPedido = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Pedido", "idPedido", idPedido, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}

				break;
			}
			case 3: {
				System.out.println("Inserta el id de la factura ");
				String idFactura = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Pedido", "idFactura", idFactura, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				break;
			}
			case 4:{
				System.out.println("Inserta el id del producto ");
				String idProducto = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Pedido", "idProducto", idProducto, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				break;
			}
			case 5:{
				System.out.println("Inserta la cantidad");
				String cantidad = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comprobarComparador(comparador, "LIKE")) {
					ejecutarOperacion(isDelete, "Pedido", "Cantidad", cantidad, comparador);
				}
				else {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				break;
			}
			}// switch Facturas
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL ");
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}// selectFacturas

	private static String getComparadorDeseado() {
		int opcOp;
		String comparador = "=";
		
		System.out.println("1. Buscar igual que el valor introducido. ");
		System.out.println("2. Buscar menor que el valor introducido. ");
		System.out.println("3. Buscar mayor que el valor introducido. ");
		System.out.println("4. Buscar parecido que el valor introducido. ");	
		opcOp = sc.nextInt();
		sc.nextLine();	
		
		switch (opcOp) {
		case 1: {
			comparador = "=";
			break;
		}
		case 2: {
			comparador = "<";
			break;
		}
		case 3: {
			comparador = ">";
			break;
		}
		case 4: {
			comparador = "LIKE";
			break;
		}
		}// switch
		return comparador;
	}// get operador
	
	// Funcion que comprueba si el comparador que se va a utilizar en una query es valido para el tipo de datos
	// Por ejemplo no permitiria que se usara un comparador LIKE para comparar valores numericos
	private static Boolean comprobarComparador(String comparador, String comparadorNoValido) {
		Boolean esValido = true;
		
		if (comparador.equals(comparadorNoValido)) {
			esValido = false;
		}
		
		return esValido;
	}


	// FUNCIONES PARA REALIZAR LAS MODIFICACIONES
	static void modificarTabla(String tabla) {
		int id = 0;
		String columna;
		String nuevoValor;
		
		try {			
			System.out.println("Inserta el id del elemento a modificar");
			id = sc.nextInt();
			sc.nextLine();
			
			System.out.println(MetodosDataBase.getColumnasTabla(tabla));
			System.out.println("Inserta el valor a modificar (ESCRIBE EL NOMBRE COMPLETO)");
			columna = sc.nextLine();
			System.out.println("Inserta el nuevo valor");
			nuevoValor = sc.nextLine();
			
			MetodosDataBase.modificar(tabla, columna, id, nuevoValor);
			
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL ");
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}
	
	
	static void eliminarDatos() {
	int opc = 0;
		
		System.out.println("1. Tabla Mesa");
		System.out.println("2. Tabla Productos");
		System.out.println("3. Tabla Factura");
		System.out.println("4. Tabla Pedido");
		System.out.println("Elige sobre que tabla quieres realizar una modificacion ");
		opc = sc.nextInt();
		sc.nextLine();
		
		try {
			switch (opc) {
			case 1: {
				selectMesa(true);
				break;
			}
			case 2: {
				//MetodosDataBase.borrarTabla("Productos");
				break;
			}
			case 3: {
				//MetodosDataBase.borrarTabla("Factura");
				break;
			}
			case 4: {
				selectProductos(true);
				break;
			}
			default:
				System.out.println("Opción no válida");
			}
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL " + sqlE.getMessage());
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
		
		
	}// eliminar datos
	
	// funcion para borrar las tablas de la base de datos
	static void borrarTabla() {
		int opc = 0;
			
		System.out.println("1. Tabla Mesa");
		System.out.println("2. Tabla Productos");
		System.out.println("3. Tabla Factura");
		System.out.println("4. Tabla Pedido");
		System.out.println("Elige que tabla quieres borrar ");
		opc = sc.nextInt();
		sc.nextLine();
			
		try {
			switch (opc) {
				case 1: {
					if (MetodosDataBase.borrarTabla("Mesa")) {
						System.out.println("Tabla borrada correctamente ");
					}
					else {
						System.out.println("No se pudo borrar");
					}
					break;
				}
				case 2: {
					if (MetodosDataBase.borrarTabla("Productos")) {
						System.out.println("Tabla borrada correctamente ");
					}
					else {
						System.out.println("No se pudo borrar");
					}
					break;
				}
				case 3: {
					if (MetodosDataBase.borrarTabla("Factura")) {
						System.out.println("Tabla borrada correctamente ");
					}
					else {
						System.out.println("No se pudo borrar");
					}
					break;
				}
				case 4: {
					if (MetodosDataBase.borrarTabla("Pedido")) {
						System.out.println("Tabla borrada correctamente ");
					}
					else {
						System.out.println("No se pudo borrar");
					}
					break;
				}
				default:
					System.out.println("Opción no válida");
			}
		}
		catch(SQLSyntaxErrorException syntaxE) {
			System.out.println("ERROR DE SINTAXIS, INSERTA BIEN LOS DATOS ");
		}
		catch(SQLException sqlE) {
			System.out.println("ERROR SQL " + sqlE.getMessage());
		}
		catch(ClassNotFoundException connE) {
			System.out.println("ERROR EN CONECTAR EN LA BASE DE DATOS ");
		}
		catch(Exception e) {
			System.out.println("ERROR INESPERADO");
		}
	}// borrar tabla
	
	
	// funcion qeu se encarga de hacer un select o un delete segun de donde se llame a la funcion
	private static void ejecutarOperacion(boolean isDelete, String tabla, String columna, String valor, String comparador) 
	        throws SQLException, ClassNotFoundException {
	    if (isDelete) {
	        MetodosDataBase.borrarDatosTabla(tabla, columna, valor, comparador);
	    } else {
	    	switch (tabla) {
            case "Mesa":
                MetodosDataBase.listadoMesa(columna, valor, comparador);
                break;
            case "Productos":
                MetodosDataBase.listadoProductos(columna, valor, comparador);
                break;
            case "Factura":
            	MetodosDataBase.listadoFactura(columna, valor, comparador);
            	break;
            case "Pedido":
            	MetodosDataBase.listadoPedido(columna, valor, comparador);
            	break;
            // Agrega más casos según las tablas que tengas
            default:
                throw new IllegalArgumentException("No se reconoce la tabla: " + tabla);
        }
	    }
	}

}
