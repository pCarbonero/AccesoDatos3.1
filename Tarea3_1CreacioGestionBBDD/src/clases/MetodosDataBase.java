package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class MetodosDataBase {

	// CREACION DE TABLAS //
	public static void crearTablaMesa() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			// Previamente habremos realizado la conexión
			conn = ConexionDB.conectar();
			// Creamos un nuevo objeto con la conexión
			stmt = conn.createStatement();
			// Definimos la sentencia de crear una nueva base de datos
			String sql = "Create table Mesa(\r\n" + "	idMesa int primary key,\r\n" + "	numComensales int,\r\n"
					+ " reserva tinyint\r\n)";
			// Ejecutar la sentencia
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// Gestionamos los posibles errores que puedan surgir durante la ejecucion de la
			// insercion
			se.printStackTrace();
		} catch (Exception e) {
			// Gestionamos los posibles errores
			e.printStackTrace();
		} finally {
			// Cerrar el objeto en uso y la conexión
			stmt.close();
			conn.close();
		}
	}

	public static void crearTablaProductos() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		try {
			// Previamente habremos realizado la conexión
			conn = ConexionDB.conectar();
			// Creamos un nuevo objeto con la conexión
			stmt = conn.createStatement();
			// Definimos la sentencia de crear una nueva base de datos
			String sql = "Create table Productos(\r\n" + "	idProducto int primary key,\r\n"
					+ "	Denominacion varchar(45),\r\n" + " Precio Decimal(10,2)\r\n)";
			// Ejecutar la sentencia
			stmt.executeUpdate(sql);
		} catch (SQLException se) {
			// Gestionamos los posibles errores que puedan surgir durante la ejecucion de la
			// insercion
			se.printStackTrace();
		} catch (Exception e) {
			// Gestionamos los posibles errores
			e.printStackTrace();
		} finally {
			// Cerrar el objeto en uso y la conexión
			stmt.close();
			conn.close();
		}
	}

	public static boolean crearTablaFactura() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		boolean creada = false;

		if (comprobarExistencia("Mesa")) {
			try {
				// Previamente habremos realizado la conexión
				conn = ConexionDB.conectar();
				// Creamos un nuevo objeto con la conexión
				stmt = conn.createStatement();
				// Definimos la sentencia de crear una nueva base de datos
				String sql = "Create table Factura(\r\n" + "	idFactura int primary key,\r\n" + "	idMesa int,\r\n"
						+ "	TipoPago varchar(45),\r\n" + " Importe decimal,\r\n"
						+ " CONSTRAINT FK_idMesa FOREIGN KEY (idMesa)\r\n" + " REFERENCES Mesa (idMesa))";
				// Ejecutar la sentencia
				stmt.executeUpdate(sql);
				creada = true;
			} catch (SQLException se) {
				// Gestionamos los posibles errores que puedan surgir durante la ejecucion de la
				// insercion
				se.printStackTrace();
			} catch (Exception e) {
				// Gestionamos los posibles errores
				e.printStackTrace();
			} finally {
				// Cerrar el objeto en uso y la conexión
				stmt.close();
				conn.close();
			}
		} // if
		return creada;
	}

	public static boolean crearTablaPedido() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		boolean creada = false;

		if (comprobarExistencia("Factura") && comprobarExistencia("Productos")) {
			try {
				// Previamente habremos realizado la conexión
				conn = ConexionDB.conectar();
				// Creamos un nuevo objeto con la conexión
				stmt = conn.createStatement();
				// Definimos la sentencia de crear una nueva base de datos
				String sql = "Create table Pedido(\r\n" + "	idPedido int primary key,\r\n" + "	idFactura int,\r\n"
						+ "	idProducto int,\r\n" + "	cantidad int,\r\n"
						+ " CONSTRAINT FK_idFactura FOREIGN KEY (idFactura)\r\n" + " REFERENCES Factura (idFactura),"
						+ " CONSTRAINT FK_idProducto FOREIGN KEY (idProducto)\r\n"
						+ " REFERENCES Productos (idProducto))";
				// Ejecutar la sentencia
				stmt.executeUpdate(sql);
				creada = true;
			} catch (SQLException se) {
				// Gestionamos los posibles errores que puedan surgir durante la ejecucion de la
				// insercion
				se.printStackTrace();
			} catch (Exception e) {
				// Gestionamos los posibles errores
				e.printStackTrace();
			} finally {
				// Cerrar el objeto en uso y la conexión
				stmt.close();
				conn.close();
			}
		} // if
		return creada;
	}

	// Funcion que comprueba la existencia de una tabla
	private static boolean comprobarExistencia(String tabla) throws SQLException {
		boolean existe = false;
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;

		try {
			conn = ConexionDB.conectar();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM " + tabla;
			lista = stmt.executeQuery(sql);
			existe = true;
		} catch (Exception e) {
			System.out.println("La tabla " + tabla + " no existe");
		} finally {
			// Cerrar el objeto en uso y la conexión
			stmt.close();
			conn.close();
		}

		return existe;
	}

	// Funcion que inserta todos los elementos de los ficheros en las tablas de la
	// base de datos
	public static boolean insertarElementos(String tabla) throws SQLException {
		boolean insertado = false;

		Connection conn = null;
		Statement stmt = null;
		try {
			// coge la ruta del fichero con los inserts de la tabla correspondiente
			BufferedReader br = new BufferedReader(new FileReader("src/ficheros/insert" + tabla + ".txt"));
			String line = "";
			conn = ConexionDB.conectar();
			stmt = conn.createStatement();

			while ((line = br.readLine()) != null) {
				stmt.execute(line);
			}
			insertado = true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cerrar el objeto en uso y la conexión
			stmt.close();
			conn.close();
		}

		return insertado;
	}

	// funcion para realizar las consultas de la tabla mesa
	public static void listadoMesa(String Columna, String datoFiltrar, String comparador) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();

		if (datoFiltrar == null || datoFiltrar.equals("")) {
			sql = "SELECT * FROM Mesa";
		} else {
			sql = "SELECT * FROM Mesa WHERE " + Columna + "  " + comparador +" " + datoFiltrar;
		}

		lista = stmt.executeQuery(sql);

		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
				// salto de linea
			System.out.println();
		} // while

		// Paso 5. Cerrar el objeto en uso y la conexión
		stmt.close();
		conn.close();

	}// listadoMesa

	public static void listadoProductos(String columna, String datoFiltrar, String comparador) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";
		int i = 0;

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();
		if (datoFiltrar == null) {
			sql = "SELECT * FROM Productos";
		} else {
			sql = "SELECT * FROM Productos WHERE " + columna + "  " + comparador +" " + datoFiltrar;				
		}

		lista = stmt.executeQuery(sql);

		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
				// salto de linea
			System.out.println();
		} // while

		// Paso 5. Cerrar el objeto en uso y la conexión
		stmt.close();
		conn.close();

	}// listado productos

	public static void listadoFactura(String columna, String datoFiltrar, String comparador) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";
		int i = 0;

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();
		if (datoFiltrar == null) {
			sql = "SELECT * FROM Factura";
		} else {
			sql = "SELECT * FROM Factura WHERE " + columna + "  " + comparador + " " + datoFiltrar;					
		}

		lista = stmt.executeQuery(sql);

		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
			System.out.println();
		} // while

		// Paso 5. Cerrar el objeto en uso y la conexión
		stmt.close();
		conn.close();

	}// listaFactura

	public static void listadoPedido(String columna, String datoFiltrar, String comparador) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();
		if (datoFiltrar == null) {
			sql = "SELECT * FROM Pedido";
		} else {
			sql = "SELECT * FROM Pedido WHERE " + columna + "  " + comparador +" " + datoFiltrar;						
		}
		


		lista = stmt.executeQuery(sql);

		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
			System.out.println();
		} // while

		// Paso 5. Cerrar el objeto en uso y la conexión
		stmt.close();
		conn.close();

	}// listado pedidos
	
	
	public static void modificar(String tabla, String columna, int id, String nuevoValor) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";
		int opc;
		Scanner sc = new Scanner(System.in);
		
		String nombreId = switch(tabla) {
			case "Mesa" -> "idMesa";
			case "Productos" -> "idProducto";
			case "Factura" -> "idFactura";
			case "Pedido" -> "idPedido";
			default -> throw new IllegalArgumentException("Unexpected value: " + tabla);
		};
		
		conn = ConexionDB.conectar();
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		sql =     "Update " + tabla
				+ " Set " + columna + " = " + nuevoValor
				+ " Where " + nombreId + " = " + id ;
		stmt.executeUpdate(sql);
		
		sql = "Select * FROM " + tabla + " WHERE " + nombreId + " = " + id;
		
		lista = stmt.executeQuery(sql);
		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
			System.out.println();
		} // while
		
		System.out.println("1. Confirmar cambios ");
		System.out.println("2. Deshacer cambios ");
		System.out.println("Elige una opción ");
		opc = sc.nextInt();
		sc.nextLine();
		
		switch (opc) {
		case 1: {
			conn.commit();
			break;
		}
		case 2: {
			conn.rollback();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}

		// Paso 5. Cerrar el objeto en uso y la conexión
		stmt.close();
		conn.close();
		//sc.close();
	}// modificar
	
	public static String getColumnasTabla(String tabla) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";

		conn = ConexionDB.conectar();
		// hacemos que para que se guarden los cambios se necesite hacer un commit
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		
		sql = "SELECT * FROM " + tabla + " LIMIT 1";

		lista = stmt.executeQuery(sql);
		
		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();
		String columnas = "";
		
		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			for (int col = 1; col <= columnCount; col++) {
				columnas += col + ". " + metaData.getColumnName(col) + "\n";
			} // for
		}
		stmt.close();
		conn.close();
		return columnas;
	}// getColumnas
	
	public static void borrarDatosTabla(String tabla, String columna, String datoFiltrar, String comparador) throws SQLException, SQLSyntaxErrorException,
																							   ClassNotFoundException, SQLIntegrityConstraintViolationException  {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";
		int opc;

		conn = ConexionDB.conectar();
		conn.setAutoCommit(false);
		stmt = conn.createStatement();
		
		if (datoFiltrar != null) {
			sql = "DELETE FROM " + tabla + " WHERE "+ columna + " " + comparador + " " + datoFiltrar;
		}
		else {
			sql = "DELETE FROM " + tabla;
		}
		
		stmt.executeUpdate(sql);
		
		sql = "Select * FROM " + tabla;
		
		lista = stmt.executeQuery(sql);
		ResultSetMetaData metaData = lista.getMetaData();
		int columnCount = metaData.getColumnCount();

		while (lista.next()) {
			// Recorrer todas las columnas de la fila actual
			System.out.println("----------------------------");
			for (int col = 1; col <= columnCount; col++) {
				System.out.print(metaData.getColumnName(col) + ": " + lista.getString(col) + "\n");
			} // for
			System.out.println();
		} // while
		
		System.out.println("1. Confirmar cambios ");
		System.out.println("2. Deshacer cambios ");
		System.out.println("Quieres borrar esos datos? ");
		opc = sc.nextInt();
		sc.nextLine();
		
		switch (opc) {
		case 1: {
			conn.commit();
			break;
		}
		case 2: {
			conn.rollback();
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + opc);
		}
		stmt.close();
		conn.close();
		
	}// borrar datos tabla
	
	
	public static boolean borrarTabla(String tabla)throws SQLException, SQLSyntaxErrorException,
	   											ClassNotFoundException {

		Connection conn = null;
		Statement stmt = null;
		String sql = "";
		boolean creada = false;

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();
		

		sql = "Drop table " + tabla;
		
		
		stmt.executeUpdate(sql);
		
		creada = true;
		
		stmt.close();
		conn.close();
		
		return creada;
	}		
}
