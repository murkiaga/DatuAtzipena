import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.sql.DatabaseMetaData;

public class AccessetikMySQLra {

	public static void main(String[] args) {

		Connection accessConection = null, mysqlConection = null;
		Statement accessStmt = null, mysqlStmt = null;
		ResultSet accessRs = null, accessRs2 = null, accessRs3 = null, mysqlRs = null;

		try {
			accessConection = DriverManager.getConnection("jdbc:ucanaccess://farmacia.mdb");
			mysqlConection = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmazia?serverTimezone=UTC", "root", "");

			mysqlStmt = mysqlConection.createStatement();
			// IRAKURRI datu baseko taula guztiak:
			DatabaseMetaData metaData = accessConection.getMetaData();
			accessRs = metaData.getTables(null, null, "%", null);

			String sqlSententzia = "";
			while (accessRs.next()) { // Taula bakoitzeko...
				String table_name = accessRs.getString(3); // see javadoc of DatabaseMetaData
				System.out.println(table_name);
				accessRs2 = metaData.getColumns(table_name, null, null, null);
				sqlSententzia = "CREATE TABLE " + table_name + " (";
				while (accessRs2.next()) { // Taulako Zutabe bakoitzeko
					System.out.println("-" + accessRs2.getString("COLUMN_NAME") + " " + accessRs2.getString("TYPE_NAME")
							+ " (" + accessRs2.getString("COLUMN_SIZE") + ") " + accessRs2.getString("NULLABLE"));
					sqlSententzia += accessRs2.getString("COLUMN_NAME") + " " + accessRs2.getString("TYPE_NAME");
					if (accessRs2.getString("TYPE_NAME").equals("VARCHAR")) {
						sqlSententzia += "(" + accessRs2.getString("COLUMN_SIZE") + ")";
					}
					if (accessRs2.getInt("NULLABLE") == 0) {
						sqlSententzia += " NOT NULL";
					}
					if (accessRs2.getString("IS_AUTOINCREMENT").equals("YES")) {
						sqlSententzia += " AUTO_INCREMENT";
					}
					sqlSententzia += ",";
				}
				sqlSententzia += "PRIMARY KEY (";
				accessRs3 = metaData.getPrimaryKeys(null, null, table_name);
				boolean lehena = true;
				while (accessRs3.next()) {
					System.out.println("Primary key: " + accessRs3.getString("COLUMN_NAME"));
					if (!lehena) {
						sqlSententzia += ",";
					} else {
						lehena = false;
					}
					sqlSententzia += accessRs3.getString("COLUMN_NAME");
				}
				sqlSententzia += ")"; // Primary key itxi
				sqlSententzia += ")"; // Zutabeak itxi
				System.out.println(sqlSententzia);
				//mysqlStmt.execute(sqlSententzia);

				//EGITEKE
				//Orain access taulan selecta egin eta mysql-n insertak
			}
			

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				if (accessConection != null)
					accessConection.close();
				if (accessStmt != null)
					accessStmt.close();
				if (accessRs != null)
					accessRs.close();
			} catch (Exception e) {

			}
		}

	}

}
