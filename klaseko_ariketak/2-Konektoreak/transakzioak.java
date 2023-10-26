import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class transakzioak {
	private Connection conexion;
	private PreparedStatement sInsert = null;
	
	transakzioak(){
		try {
			this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankua?serverTimezone=UTC", "root", "");
			System.out.println("Konexioa ezarrita.");
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			System.out.println("Conexion Erronea.");
		} 
	}
	
	public Connection get_connection() {
		return this.conexion;
	}
	
	public void close_connection() {
		try {
			if (this.conexion != null) this.conexion.close();
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		}
		
	}
	
	public void traspaso_dibisa(String izena, int mota, int kantitatea) {
		try {
			
			this.conexion.setAutoCommit(false);
			
			sInsert = conexion.prepareStatement("UPDATE euroak SET kantitatea=kantitatea-? where izena=?");
			sInsert.setInt(1, kantitatea);
			sInsert.setString(2, izena);
			sInsert.executeUpdate();
			
			sInsert = conexion.prepareStatement("UPDATE dolarraak SET kantitatea=kantitatea+? where izena=?");
			sInsert.setInt(1, kantitatea*2);
			sInsert.setString(2, izena);
			sInsert.executeUpdate();
			
			conexion.commit();
			
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
			try {
				conexion.rollback();
				System.out.println("ROLLBACK");
			} catch (Exception er) {
				System.err.println(er.getMessage());
			}
		}
	}
	
	public static void main(String[] args) {
		transakzioak t = null;
		try {
			t = new transakzioak();
			
			System.out.println("hodei-ri 2 euro pasatzen dolarretara");
			t.traspaso_dibisa("hodei", 1, 2);
	
			System.out.println("Programa bukatuta");
		} finally {
			try {
				t.close_connection();
				System.out.println("konexioak itxita");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
