package util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			File bd = new File("DBPX.db");
			if (bd.exists()) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:DBPX.db");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
