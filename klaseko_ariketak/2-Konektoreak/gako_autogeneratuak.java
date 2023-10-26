import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gako_autogeneratuak {

	public static void main(String[] args) {
		Connection conexion = null;
		ResultSet rs = null;
		PreparedStatement sInsert = null, sInsert_lerroak=null;
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/java_jdbc?serverTimezone=UTC", "root", "");
			String sql_sententzia = "INSERT INTO fakturak (bezero_dni) VALUES (?)";
			sInsert = conexion.prepareStatement(sql_sententzia, PreparedStatement.RETURN_GENERATED_KEYS);
			
			String sql_sententzia2 = "INSERT INTO faktura_lerroak (num_faktura, faktura_lerroa, laburpena, kantitatea) VALUES (?,?,?,?);";
			sInsert_lerroak = conexion.prepareStatement(sql_sententzia2, PreparedStatement.RETURN_GENERATED_KEYS);
			
			conexion.setAutoCommit(false); //Transakzioa hasi
			
			sInsert.setString(1, "78948291Q");
			sInsert.executeUpdate();
			rs = sInsert.getGeneratedKeys();
			rs.next(); //Bakarra itzuliko du, joan lehen lerro horretara.
			int fakt_zkia = rs.getInt(1);
			
			int fakt_lerro_zkia = 1;
		
			sInsert_lerroak.setInt(1, fakt_zkia);
			sInsert_lerroak.setInt(2, fakt_lerro_zkia++);
			sInsert_lerroak.setString(3, "TORLOJUAK");
			sInsert_lerroak.setInt(4, 25);
			sInsert_lerroak.executeUpdate();
			
			sInsert_lerroak.setInt(1, fakt_zkia);
			sInsert_lerroak.setInt(2, fakt_lerro_zkia++);
			sInsert_lerroak.setString(3, "AZKOINAK");
			sInsert_lerroak.setInt(4, 10);
			sInsert_lerroak.executeUpdate();
			
			conexion.commit(); //Transakzioa bukatu
			System.out.println("Eragiketak bukatuta");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.out.println("Conexion Erronea.");
			try {
				conexion.rollback();
				System.out.println("ROLLBACK");
			} catch (Exception er) {
				System.err.println(er.getMessage());
			}
		} finally {
			try {
				if (rs != null) rs.close();
				if (sInsert_lerroak != null) sInsert_lerroak.close();
				if (conexion != null) conexion.close();
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
