package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection {
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				// Cargar el driver de Oracle
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// Establecer la conexión
				conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "Audrie8a", "Audrie8a7024");
				
				System.out.println("Conexión Exitorsa");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
