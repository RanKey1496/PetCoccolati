package com.petcoccolati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dto.SignUpDTO;
import com.petcoccolati.util.ExceptionPet;

public class SignUpDAO {

	private PoolConection pool;
	private Conection conection;
	
	public SignUpDAO() {
		conection = new Conection();
		pool = new PoolConection();
	}
	
	public void createPersona(SignUpDTO persona) throws ExceptionPet{
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
			ps.setInt(1, persona.getId());
			ps.setString(2, persona.getFirst());
			ps.setString(3, persona.getLast());
			ps.setString(4, persona.getPhone());
			ps.setString(5, persona.getEmail());
			ps.setString(6, persona.getPassword());
			ps.executeUpdate();
			System.out.println("Persona creada");
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
		
		ExceptionPet excepPet = new ExceptionPet();
		excepPet.setMensajeUsuario("Usuario creado con éxito");
		throw excepPet;
	}
	
	public void searchPersona(SignUpDTO personaDTO) throws ExceptionPet{
		ExceptionPet excepWeb = new ExceptionPet();
		excepWeb.setMensajeUsuario("La persosna ya existe");
		throw excepWeb;
	}
}
