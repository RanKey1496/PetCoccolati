package com.petcoccolati.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			String query = "INSERT INTO Mascotas (Nombre, Raza, Genero, Especie, Peso, Chip, Id) values (?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);			
			ps.setString(1, pet.getName());
			ps.setString(2, pet.getBreed());
			ps.setString(3, pet.getGenre());
			ps.setString(4, pet.getSpecies());
			ps.setInt(5, pet.getWeight());
			ps.setString(6, pet.getDni());
			
			ps.executeUpdate();
			System.out.println("Pet creada");
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();			
			excepPet.setExceptionOriginal(e);
			try {
				throw excepPet;
			} catch (ExceptionPet e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();			
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

}
