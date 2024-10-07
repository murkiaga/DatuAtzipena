import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class uCanAccessMetadata {
	
	public static void main(String[] args) {
		String datuBaseUrla = "jdbc:ucanaccess://farmacia.mdb";
		
		try(Connection conn = DriverManager.getConnection(datuBaseUrla)){
			DatabaseMetaData metadatuak = conn.getMetaData();
			//ResultSet taulak = metadatuak.getCatalogs();
			ResultSet taulak = metadatuak.getTables(null, null, "%", new String[]{"TABLE"});
			while(taulak.next()) {
				String taulaIzena = taulak.getString("TABLE_NAME");
				System.out.println("-"+taulaIzena);
				ResultSet zutabeak = metadatuak.getColumns(null, null, taulaIzena, null);
				while(zutabeak.next()) {
					System.out.print("--"+zutabeak.getString("COLUMN_NAME") + " " + zutabeak.getString("TYPE_NAME"));
					if (zutabeak.getString("TYPE_NAME").equals("VARCHAR")) {
						System.out.print(" " +zutabeak.getString("COLUMN_SIZE"));
					}
					System.out.println();
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
			System.out.println("bukatuta");
		}
	}
}
