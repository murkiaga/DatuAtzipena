import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.CallableStatement;

public class prepareCall_procedura {

	public static void main(String[] args) {
		
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		CallableStatement cs = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://192.168.37.22:8899/bankua", "root", "secret");
			System.out.println("Conexión Correcta.");
			String pertsona = "iker";

			cs = conexion.prepareCall("{call get_euro_pertsona(?, ?)}");
			
			cs.setString(1, pertsona);
			cs.registerOutParameter(2, java.sql.Types.DOUBLE);
			cs.execute();
			
			double euroak = cs.getDouble(2);
			System.out.println(pertsona+"-n diru kantitatea: "+ euroak);
			
		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.out.println("Konexioan errorea.");
			
		}finally {
			try {
				if (cs != null) cs.close();
				if (conexion != null) {
					conexion.close();
					System.out.println("Konexioa itxita");
				}
				if (st != null) {
					st.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
				System.err.println(e2.getMessage());
			}
		}
	}
}
