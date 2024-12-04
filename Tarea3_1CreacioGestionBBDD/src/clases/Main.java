package clases;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		try {
			ConexionDB.useDATABASE();
			menu();
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
			System.out.println("2. Insertar personas en la tabla ");
			System.out.println("3. Listado personas por edad ");
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

}
