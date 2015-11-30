package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.AdminDashDAOInt;
import com.petcoccolati.dto.PersonalDTO;
import com.petcoccolati.util.ExceptionPet;

public class AdminDashDAO implements AdminDashDAOInt{
	
	private Conection conection;
	private PoolConection pool;
	private static final Logger logger = Logger.getLogger(AdminDashDAO.class);

	public AdminDashDAO() {
		conection = new Conection();
		pool = new PoolConection();
		logger.info("Se creó un AdminDash");
	}
	
	@Override
	public void deletePersonal(String cedula) throws ExceptionPet {
		
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conexion = null;
		
		Connection conn = null;
		try {
			conexion = pool.getConexion();
			System.out.println(conexion.toString());
			conn = conexion.getConexion();			
			st = conn.createStatement();
			String query = "DELETE FROM Personal WHERE Cedula = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, Integer.valueOf(cedula));
			ps.executeUpdate();
			logger.info("Personal borrado");
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error borrando persona");			
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error borrando persona");	
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		}finally{
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { st.close(); } catch (Exception e) { /* ignored */ }
		    try {
		    	pool.liberarConexion(conexion);
		    } catch (Exception e) { /* ignored */ }
			
		}
	}

	@Override
	public void addPersonal(PersonalDTO personal) throws ExceptionPet {
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conexion = null;
		
		Connection conn = null;
		try {
			conexion = pool.getConexion();
			System.out.println(conexion.toString());
			conn = conexion.getConexion();
			
			st = conn.createStatement();
			String query = "INSERT INTO Personal (Cedula, Nombre, Apellido, Telefono, Direccion, Contrasena, Tipo) values (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, personal.getId());
			ps.setString(2, personal.getName());
			ps.setString(3, personal.getLastName());
			ps.setString(4, personal.getPhone());
			ps.setString(5, personal.getAddress());
			ps.setString(6, personal.getPass());
			ps.setString(7, "Cajero");
			ps.executeUpdate();
			logger.info("Personal creado");
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado persona");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado persona");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		}finally{
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { st.close(); } catch (Exception e) { /* ignored */ }
		    try {
		    	pool.liberarConexion(conexion);
		    } catch (Exception e) { /* ignored */ }
			
		}		
	}

}
