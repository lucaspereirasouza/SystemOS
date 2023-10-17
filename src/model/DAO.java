package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private String driver = "com.mysql.cj.jdbc.Driver";
//	private String url = "jdbc:mysql://127.0.0.1:3306/databaseos";
	private String url = "jdbc:mysql://10.26.45.241:3307/databaseos";
	private String user = "root";
	private String password = "123@senac";
	// Criação de um objeto para uso da classe Connection(JDBC)
	private Connection con;
	/**
	 * Método responsavel pela conexão jdbc no sistema
	 * @return con
	 */
	public Connection conectar() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user,password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
