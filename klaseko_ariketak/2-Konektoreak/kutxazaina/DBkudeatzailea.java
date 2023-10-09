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
	private String DB_URL = "jdbc:"+DB_MOTA+"://"+IP+":"+PORT+"/"+DB_NAME+"?serverTimezone=UTC";
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
			System.out.println(DB_URL);
			this.conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println(DB_NAME+"-ra konexioa ezarrita.");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.out.println(DB_NAME+"-ra konektatzean errorea.");
		}
	}

	public String getIP() {
		return IP;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getPASS() {
		return PASS;
	}

	public void setPASS(String pASS) {
		PASS = pASS;
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
	
	public float get_erabiltzaile_dirua(String erab_izena) {
		//TODO
		return 0.0f;
	}
	
	public void set_erabiltzaile_arteko_transferentzia(String erab1, String erab2, float kantitatea) {
		//TODO erab1-ri kantitatea kendu eta erab2-ri kantitatea gehitu. 
	}
	
	public void set_erabiltzaileari_mugimendu_berria(String erab1, float kantitatea) {
		//TODO erabiltzaileari mugimendu berria sortu (kantitatea + edo - izan daiteke)
	}
	
}
