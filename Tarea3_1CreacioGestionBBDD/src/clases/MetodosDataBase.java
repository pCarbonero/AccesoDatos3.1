package clases;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

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
	public static void listadoMesa(String Columna, String datoFiltrar, String operador) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();

		if (datoFiltrar == null || datoFiltrar.equals("")) {
			sql = "SELECT * FROM Mesa";
		} else {
			sql = "SELECT * FROM Mesa WHERE " + Columna + "  " + operador +" " + datoFiltrar;
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

	public static void listadoProductos(String columna, String datoFiltrar) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
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
			sql = "SELECT * FROM Productos WHERE " + columna + " = " + datoFiltrar;
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

	public static void listadoFactura(String columna, String datoFiltrar) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
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
			sql = "SELECT * FROM Factura WHERE " + columna + " = " + datoFiltrar;
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

	public static void listadoPedido(String columna, String datoFiltrar) throws SQLException, SQLSyntaxErrorException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet lista = null;
		String sql = "";

		conn = ConexionDB.conectar();
		stmt = conn.createStatement();
		if (datoFiltrar == null) {
			sql = "SELECT * FROM Pedido";
		} else {
			sql = "SELECT * FROM Pedido WHERE " + columna + " = " + datoFiltrar;
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

}
