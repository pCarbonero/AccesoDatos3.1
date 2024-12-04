package clases;

import java.sql.SQLException;
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

			}
		}
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

		}
	}

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
	static void listarElementos() {
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
		default:
			System.out.println("Opción no válida");
		}		
	}// funcion inserts

	private static void selectMesa() {
		int opcMesa;
		System.out.println("1. Por idMesa ");
		System.out.println("2. Por número de comensales");
		System.out.println("3. Por reserva ");
		System.out.println("Elige la columna para filtrar los datos ");
		opcMesa = sc.nextInt();
		switch (opcMesa) {
		case 1: {
			System.out.println("Inserta el id de la mesa ");
			int idMesa = sc.nextInt();
			// TODO Funcion select Mesa
			break;
		}
		case 2:{
			System.out.println("Inserta el número de comensales ");
			int numComensales = sc.nextInt();
			// TODO funcion select Mesa
		}
		case 3:{
			System.out.println("Buscar por mesas con reserva? S/N");
			String siNo = sc.next();
			sc.nextLine();
			Boolean reserva = (siNo.equalsIgnoreCase("s"))? true : false;
			// TODO funcion select Mesa
		}
		
		}// switch Mesa
	}
	
	// TODO Funcion para imprimir los datos de las consultas (2 sobrecargas: para listas y para elementos sueltos)

	

}
