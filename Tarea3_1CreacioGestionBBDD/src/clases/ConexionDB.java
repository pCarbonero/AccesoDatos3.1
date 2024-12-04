package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
	// Funcion que realiza una conexion a la base de datos
	public static Connection conectar() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String conexionUrl = "jdbc:mysql://dns11036.phdns11.es:3306/ad2425_pcarbonero?"
				+ "user=pcarbonero&password=12345";
		Connection con = DriverManager.getConnection(conexionUrl);
		System.out.println(con.toString());
		return con;
	}

	public static void useDATABASE() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;

		conn = conectar();
		stmt = conn.createStatement();
		String useBD = "use ad2425_pcarbonero";
		stmt.execute(useBD);
	}

}
