import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBC_insert {

	public static void main(String[] args) {
		String datuBaseIzena = "java_jdbc";
		String host = "localhost";
		String port = "3306";
		String parAdic = "serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + datuBaseIzena + "?" + parAdic;
		
		try (
			Connection c = DriverManager.getConnection(urlConnection, "root", "");
			Statement s = c.createStatement()
			){ 
			int nFilak = s.executeUpdate(
					"INSERT INTO ERABILTZAILEAK (DNI, ABIZENAK, CP) VALUES "
					+ "('78947362X', 'ABIZ1', '44320'),"
					+ "('18941362Z', 'ABIZ2', '54321'),"
					+ "('55947365Y', 'ABIZ3', '64320');");
	
			System.out.println(nFilak + "fila sartu dira.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}