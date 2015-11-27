package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.SignUpDAOInt;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class SignUpDAO implements SignUpDAOInt{

	private PoolConection pool;
	private Conection conection;
	
	private static final Logger logger = Logger.getLogger(SignUpDAO.class);
	
	public SignUpDAO() {
		conection = new Conection();
		pool = new PoolConection();
		logger.info("Se creó un SignUpDAO");
	}
	
	public void createPersona(PersonaDTO persona) throws ExceptionPet{
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
			String query = "INSERT INTO Clientes (Cedula, Nombre, Apellido, Telefono, Email, Contrasena) values (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, persona.getId());
			ps.setString(2, persona.getFirst());
			ps.setString(3, persona.getLast());
			ps.setString(4, persona.getPhone());
			ps.setString(5, persona.getEmail());
			ps.setString(6, persona.getPassword());
			ps.executeUpdate();
			logger.info("Persona creada");
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado persona");
			excepPet.setMensajeTecnico("Error en crearPersona de la clase SigUpDAO (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado persona");
			excepPet.setMensajeTecnico("Error en crearPersona de la clase SigUpDAO");
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
	
	public void searchPersona(PersonaDTO persona) throws ExceptionPet{
		ExceptionPet excepWeb = new ExceptionPet();
		excepWeb.setMensajeUsuario("La persosna ya existe");
		throw excepWeb;
	}

	public void updatePersona(PersonaDTO persona) throws ExceptionPet{
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
			String query = ("UPDATE Clientes SET Nombre=?, Contrasena=?, Email=? WHERE Clientes.Cedula = ?");
			ps = conn.prepareStatement(query);
			ps.setString(1, persona.getFirst());
			ps.setString(2, persona.getPassword());
			ps.setString(3, persona.getEmail());
			ps.setString(4, persona.getId());
			ps.executeUpdate();
			logger.info("Persona actualizada");
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error actualizando persona");
			excepPet.setMensajeTecnico("Error en updatePersona de la clase SigUpDAO (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error actualizando persona");
			excepPet.setMensajeTecnico("Error en updatePersona de la clase SigUpDAO");
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
