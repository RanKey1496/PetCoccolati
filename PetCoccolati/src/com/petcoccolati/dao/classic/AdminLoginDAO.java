package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.AdminLoginDAOInt;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class AdminLoginDAO implements AdminLoginDAOInt{

	private PoolConection pool;
	private Conection conection;
	
	public AdminLoginDAO() {
		conection = new Conection();
		pool = new PoolConection();
	}
	
	@Override
	public boolean exists(String id, String password) throws ExceptionPet {
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Conection conexion = null;
		
		Connection conn = null;
		try {
			conexion = pool.getConexion();
			System.out.println(conexion.toString());
			conn = conexion.getConexion();
			int cedula = Integer.valueOf(id);
			st = conn.createStatement();
			String query = "SELECT * FROM Personal WHERE Cedula=? AND Contrasena=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, cedula);
			ps.setString(2, password);			
			rs = ps.executeQuery();
			
			if(rs.next()){				
				return true;
			}
			
		return false;			
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona de la clase LoginDAO");
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
