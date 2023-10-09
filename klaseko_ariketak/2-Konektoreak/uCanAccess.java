import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class uCanAccess {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
	    	 conn = DriverManager.getConnection("jdbc:ucanaccess://farmacia.mdb");

	         stmt = conn.createStatement();
	         rs = stmt.executeQuery("SELECT nombre from medicamentos");
	         while (rs.next()) {
	             String usuario = rs.getString("nombre");
	             System.out.println(usuario);
	         }  

	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      } finally {
	    	  try {
	    		  if (conn != null) conn.close();
	    		  if (stmt != null) stmt.close();
	    		  if (rs != null) rs.close();
	    	  } catch(Exception e) {
	    		  
	    	  }
	      }
	}
}
