package com.petcoccolati.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

	private static Connection connection = null;

	private static String _bd = "petcoccolati";
	private static String url = "jdbc:mysql://db4free.net/" + _bd;
	private static String user = "rankey";
	private static String password = "totoycent50";

	public Conection() {

		try {
			Class.forName("com.mysql.jdbc.Connection");
			connection = (Connection) DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Conexi-n a base de datos " + url + " . . . Ok");
			}
		} catch (SQLException ex) {
			System.out.println("Hubo un problema al intentar conecarse a la base de datos" + url);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
	}

}
