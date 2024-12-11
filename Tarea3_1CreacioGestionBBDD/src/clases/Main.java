package clases;

import java.sql.SQLException;
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
			System.out.println("0. Salir ");
			opc = sc.nextInt();
			sc.nextLine();
			
			switch (opc) {
			case 1: {
				crearTablas();
				break;
			}
			case 2: {
				insertarDatos();
			}
			case 3:{
				listarElementos();
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
			selectMesa();
			break;
		}
		case 2:{
			selectProductos();
			break;
		}
		case 3:{
			selectFactura();
			break;
		}
		case 4:
			selectPedido();
			break;
		default:
			System.out.println("Opción no válida");
		}		
	}// funcion inserts

	static void modificarElementos() {
		
	}
	
	
	
	
	
	
	
	
	// funcines para realizar los select en la base de datos
	private static void selectMesa() {
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
				// se llama al metodo que lista las mesas con los parametros con valores predetrmindos
				MetodosDataBase.listadoMesa("", null, "");
				break;
			}
			case 2: {
				System.out.println("Inserta el id de la mesa ");
				String idMesa = sc.nextLine();
				
				comparador = getComparadorDeseado();
				
				if (comparador.equals("LIKE")) {
					System.out.println("Buscar un valor parecido no es posible en esta opción");
				}
				else {
					MetodosDataBase.listadoMesa("idMesa", idMesa, comparador);
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
					MetodosDataBase.listadoMesa("numComensales", numComensales, comparador);
				}
				
				break;
			}
			case 4:{
				System.out.println("Buscar por mesas con reserva? S/N");
				String siNo = sc.next();
				sc.nextLine();
				String reserva = (siNo.equalsIgnoreCase("s"))? "1" : "0";

				MetodosDataBase.listadoMesa("reserva", reserva, "=");				
				
				break;
			}	
			}// switch Mesa
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
	
	private static void selectProductos() throws SQLException{
		int opcProductos;
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
				MetodosDataBase.listadoProductos("", null);
				break;
			}
			case 2: {
				System.out.println("Inserta el id del producto ");
				String idProducto = sc.nextLine();

				MetodosDataBase.listadoProductos("idProducto", idProducto);
				break;
			}
			case 3:{
				System.out.println("Inserta el nombre del producto ");
				String denominacion = sc.nextLine();

				MetodosDataBase.listadoProductos("Denominacion", denominacion);
				break;
			}
			case 4:{
				System.out.println("Inserta el precio del producto");
				String precio = sc.nextLine();

				MetodosDataBase.listadoProductos("Precio", precio);
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

	private static void selectFactura() throws SQLException{
		int opcFactura;
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
				MetodosDataBase.listadoFactura("", null);
				break;
			}
			case 2: {
				System.out.println("Inserta el id de la factura ");
				String idFactura = sc.nextLine();

				MetodosDataBase.listadoFactura("idFactura", idFactura);
				break;
			}
			case 3:{
				System.out.println("Inserta el nombre del producto ");
				String idMesa = sc.nextLine();

				MetodosDataBase.listadoFactura("idMesa", idMesa);
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
						
				MetodosDataBase.listadoProductos("tipoPago", tipoPago);
				break;
			}	
			case 5:{
				System.out.println("Inserta el importe");
				String importe = sc.nextLine();

				MetodosDataBase.listadoProductos("Importe", importe);
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
	
	private static void selectPedido() throws SQLException{
		int opcPedido;
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
				MetodosDataBase.listadoPedido("", null);
				break;
			}
			case 2:{
				System.out.println("Inserta el id del pedido ");
				String idPedido = sc.nextLine();

				MetodosDataBase.listadoPedido("idPedido", idPedido);
				break;
			}
			case 3: {
				System.out.println("Inserta el id de la factura ");
				String idFactura = sc.nextLine();

				MetodosDataBase.listadoPedido("idFactura", idFactura);
				break;
			}
			case 4:{
				System.out.println("Inserta el id del producto ");
				String idProducto = sc.nextLine();

				MetodosDataBase.listadoPedido("idProducto", idProducto);
				break;
			}
			case 5:{
				System.out.println("Inserta la cantidad");
				String cantidad = sc.nextLine();

				MetodosDataBase.listadoPedido("Cantidad", cantidad);
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
}
