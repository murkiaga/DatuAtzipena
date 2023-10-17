import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class resultset_updatable {

	public static void main(String[] args) {
		
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
        String dbIzena = "java_jdbc";
		
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/"+dbIzena+"?serverTimezone=UTC", "root", "");
			st = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("SELECT * from erabiltzaileak");
					
			conexion.setAutoCommit(false);
			
			rs.last(); //Joan azken lerrora

			rs.previous(); //Aurreko lerrora joan
			rs.updateString("izena", "joxepe");
			rs.updateRow();
	
			rs.previous();
			rs.deleteRow(); //Lerroa ezabatu

			rs.moveToInsertRow(); //Insert bukaeran, baina gogoratu posizio hau
			rs.updateString("izena", "berria");
			rs.updateString("emaila", "berria@berria.com");
			rs.insertRow();
			
			rs.moveToCurrentRow(); //Bueltatu gogoratu duzun posiziora
			rs.deleteRow();
			
			/*
			rs.last();
			System.out.println(rs.getString(2));
			rs.previous();
			System.out.println(rs.getString(2));
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt(); //Itxaron DBan eskuz (edo beste programa batek) aldaketak egin arte
			rs.last();
			rs.refreshRow();

			System.out.println(rs.getString(2));
			rs.previous();
			System.out.println(rs.getString(2));
			 */
			
			conexion.commit();
			System.out.println("Ondo egin da");
		} catch (Exception e) {
			try {
				conexion.rollback();
				System.out.println("ROLLBACK" + e.getMessage());
			}catch (Exception ex) {
				System.err.println("Ultra error");
			}
		} finally {
			try {
				if (conexion != null) conexion.close();
				if (rs != null) rs.close();
				if (st != null) st.close();
			} catch (Exception e) {
				System.err.println("ezin libera baliabideak");
			}
		}
	}
}