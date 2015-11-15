package com.petcoccolati.bd;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {

	private static String _bd = "petcoccolati";
	private static String url = "jdbc:mysql://db4free.net:3306/" + _bd;
	private static String user = "rankey";
	private static String password = "totoycent50";

	public Conection() {
	}
	
	private String name;

	public Conection(String name) {
		this.name = name;
	}
	
	public static Connection getConexion() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(url, user, password);
			if (connection != null) {
				System.out.println("Conexi-n a base de datos " + url + " . . . Ok");
			}
		} catch (SQLException ex) {
			System.out.println("Hubo un problema al intentar conecarse a la base de datos" + url);
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}
		return connection;
	}	
	
	public String toString(){
		return "Conexion "+name;
	}
}
