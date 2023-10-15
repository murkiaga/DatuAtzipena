import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_konexioa {

	public static void main(String[] args) {
		String datuBaseIzena = "java_jdbc";
		String host = "localhost";
		String port = "3306";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;

		String db_erabiltzailea = "root";
		String db_pasahitza = "";

		Connection c = null;
		try {
			c = DriverManager.getConnection(urlConnection, db_erabiltzailea, db_pasahitza);
			System.out.println("Datu basera konexioa izarrita");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				if (c != null) c.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
