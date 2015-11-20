package com.petcoccolati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public class PetDAO {
	
	private PoolConection pool;
	private Conection conection;
	
	public PetDAO() {
		conection = new Conection();
		pool = new PoolConection();
	}
	
	public void createPet(PetDTO pet) throws ExceptionPet {
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
			String query = "INSERT INTO Mascotas (Nombre, Raza, Genero, Especie, Peso, Chip, Cliente_Cedula) values (?, ?, ?, ?, ?, ?, 1039290068)";
			ps = conn.prepareStatement(query);	
			ps.setString(1, pet.getName());
			ps.setString(2, pet.getBreed());
			ps.setString(3, pet.getGenre());
			ps.setString(4, pet.getSpecies());
			ps.setInt(5, pet.getWeight());
			ps.setInt(6, pet.getDni());
			System.out.println("Pet creada");
			ps.executeUpdate();
			
		} catch (SQLException e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado Pet");
			excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (NumberFormatException e){
				ExceptionPet excepPet = new ExceptionPet();
				excepPet.setMensajeUsuario("Error creado Pet");
				excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO (SQLException)");
				excepPet.setExceptionOriginal(e);
				throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creando Pet");
			excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO");
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
		excepPet.setMensajeUsuario("Pet creado con éxito");
		throw excepPet;
	}

	public List<PetDTO> listaPets(PersonaDTO persona) throws ExceptionPet {			
		
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
			String query = "SELECT * FROM Mascotas WHERE Cliente_Cedula=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, persona.getId());	
			
			rs = ps.executeQuery();
			
			List<PetDTO> petList = new ArrayList<>();
			
			while(rs.next()){
				PetDTO pet = new PetDTO();
				pet.setId(rs.getInt("Id"));
				pet.setName(rs.getString("Nombre"));
				pet.setGenre(rs.getString("Genero"));
				pet.setSpecies(rs.getString("Especie"));
				pet.setDni(rs.getInt("Chip"));
				pet.setWeight(rs.getInt("Peso"));
				pet.setBreed(rs.getString("Raza"));
				petList.add(pet);				
			}
			
			return petList;
			
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
