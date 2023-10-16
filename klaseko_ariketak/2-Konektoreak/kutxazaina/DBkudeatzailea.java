package kutxazain_automatikoa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBkudeatzailea {
	private String IP = "localhost";
	private String PORT = "3306";
	private String DB_NAME = "";
	private String DB_MOTA = "mysql";
	private String DB_URL = "";
	private String USER = "root";
	private String PASS = "";
	private Connection conexion = null;
	
	DBkudeatzailea(String db_mota, String ip, String port, String DbName, String user, String pass) {
		this.DB_MOTA = db_mota;
		this.IP = ip;
		this.PORT = port;
		this.DB_NAME = DbName;
		this.USER = user;
		this.PASS = pass;
		this.DB_URL = "jdbc:"+DB_MOTA+"://"+IP+":"+PORT+"/"+DB_NAME+"?serverTimezone=UTC";
		
		try {
			//System.out.println(DB_URL);
			this.conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println(DB_NAME+"-ra konexioa ezarrita.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println(DB_NAME+"-ra konektatzean errorea.");
		}
	}
	
	public void setAutocommita(boolean modua) throws Exception {
		this.conexion.setAutoCommit(modua);
	}
	
	public void egin_rollback() throws Exception {
		this.conexion.rollback();
	}
	
	public void egin_commit() throws Exception {
		this.conexion.commit();
	}
	
	public void print_taula_balioak(String taula, String where_klausula, String where_balioa) {
		ResultSet rs = null;
		PreparedStatement ps_select = null;
		try {
			String sql_sententzia = "SELECT * FROM "+taula;
			
			if ((where_klausula != "") && (where_klausula != null)) {
				sql_sententzia += " WHERE "+where_klausula+" = ?";
			}
			
			ps_select = this.conexion.prepareStatement(sql_sententzia);
			if ((where_klausula != "") && (where_klausula != null)) {
				ps_select.setString(1, where_balioa);
			}
			rs = ps_select.executeQuery();

			while (rs.next()) {
	            System.out.print("Erabiltzailea: " + rs.getString("izena"));
	            System.out.println(", kantitatea: " + rs.getString("kantitatea"));
	         }
		} catch (Exception e) {
			System.err.println(e.getLocalizedMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (ps_select != null) ps_select.close();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
	}
	
	public float get_erabiltzaile_dirua(String erab_izena, String taula) {
		//TODO
		return 0.0f;
	}
	
	public void set_erabiltzaile_arteko_transferentzia(String erab1, String erab2, float kantitatea) {
		//TODO erab1-ri kantitatea kendu eta erab2-ri kantitatea gehitu. 
	}
	
	public void set_erabiltzaileari_mugimendu_berria(String erab1, int kantitatea) throws Exception {
		//TODO erabiltzaileari mugimendu berria sortu (kantitatea + edo - izan daiteke)
		String taula = "user_movements";
		String izena = "name";
		String kant = "amount";
		if (this.DB_NAME.equals("kutxabank")) {
			taula = "erabiltzaile_mugimenduak";
			izena = "izena";
			kant = "kantitatea";
		}
		
		PreparedStatement ps_select = null;
		try {
			String sql_sententzia = "INSERT INTO "+taula+" ("+izena+","+kant+") VALUES (?, ?)";
			
			ps_select = this.conexion.prepareStatement(sql_sententzia);
			ps_select.setString(1, erab1);
			ps_select.setInt(2, kantitatea);
			if (this.DB_NAME.equals("bbva")) { //Errorea sortzen nahita, ikusteko rollback dabilen
				ps_select.setString(2, "errorea?");
			}
			ps_select.executeUpdate();
			
		} finally {
			try {
				if (ps_select != null) ps_select.close();
			} catch (Exception e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
	}
}
