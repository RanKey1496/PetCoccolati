package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.LoginDAOInt;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class LoginDAO implements LoginDAOInt {

	private PoolConection pool;
	private Conection conection;
	
	public LoginDAO() {
		conection = new Conection();
		pool = new PoolConection();
	}
	
	public PersonaDTO searchPersona(PersonaDTO persona) throws ExceptionPet{
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
			String query = "SELECT * FROM Clientes WHERE Email=? AND Contrasena=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, persona.getEmail());
			ps.setString(2, persona.getPassword());
			
			rs = ps.executeQuery();
			while(rs.next()){
				PersonaDTO usuario = new PersonaDTO();
				usuario.setId(rs.getString("Cedula"));
				usuario.setFirst(rs.getString("Nombre"));
				usuario.setLast(rs.getString("Apellido"));
				usuario.setPhone(rs.getString("Telefono"));
				usuario.setEmail(rs.getString("Email"));
				usuario.setPassword(rs.getString("Contrasena"));
				return usuario;
			}
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
		return null;
	}

}
