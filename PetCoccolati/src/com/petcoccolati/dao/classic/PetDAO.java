package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.PetDAOInt;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public class PetDAO implements PetDAOInt{

	private PoolConection pool;
	private Conection conection;
	private static final Logger logger = Logger.getLogger(PetDAO.class);
	
	public PetDAO() {
		conection = new Conection();
		pool = new PoolConection();
		logger.info("Se creó un PetDAO");
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
			String query = "INSERT INTO Mascotas (Nombre, Raza, Genero, Especie, Peso, Chip, Cliente_Cedula) values (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, pet.getName());
			ps.setString(2, pet.getBreed());
			ps.setString(3, pet.getGenre());
			ps.setString(4, pet.getSpecies());
			ps.setInt(5, pet.getWeight());
			ps.setString(6, pet.getDni());
			ps.setString(7, pet.getCedula());
			ps.executeUpdate();
			logger.info("Se Pet creada");

		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado Pet");
			excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (NumberFormatException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado Pet");
			excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (Exception e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creando Pet");
			excepPet.setMensajeTecnico("Error en createPet de la clase PetDAO");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				st.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				pool.liberarConexion(conexion);
			} catch (Exception e) {
				/* ignored */ }

		}
	}

	public List<PetDTO> listaPets(String cedula) throws ExceptionPet {

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
			logger.info("Cedula del usuario: " + cedula);
			ps = conn.prepareStatement(query);
			ps.setString(1, cedula);
			
			rs = ps.executeQuery();

			List<PetDTO> petList = new ArrayList<>();
			
			while (rs.next()) {
				PetDTO pet = new PetDTO();
				pet.setId(rs.getInt("Id"));
				pet.setName(rs.getString("Nombre"));
				pet.setGenre(rs.getString("Genero"));
				pet.setSpecies(rs.getString("Especie"));
				pet.setDni(rs.getString("Chip"));
				pet.setWeight(rs.getInt("Peso"));
				pet.setBreed(rs.getString("Raza"));
				petList.add(pet);
				logger.info("Añadió la lista de Pets");
			}

			logger.info("Retornó la lista de Pets");
			return petList;

		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (Exception e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona de la clase LoginDAO");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				st.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				pool.liberarConexion(conexion);
			} catch (Exception e) {
				/* ignored */ }

		}

	}

	public List<String> listNombrePets(String cedula) throws ExceptionPet{

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
			String query = "SELECT Nombre FROM Mascotas WHERE Cliente_Cedula=?";
			logger.info("Cedula del usuario: " + cedula);
			ps = conn.prepareStatement(query);
			ps.setString(1, cedula);
			
			rs = ps.executeQuery();

			List<String> petList = new ArrayList<>();
			
			while (rs.next()) {
				petList.add(rs.getString("Nombre"));
				logger.info("Añadió la lista de nombres de sus Pets");
			}

			logger.info("Retornó la lista de nombres de sus Pets");
			return petList;

		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (Exception e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error buscando persona");
			excepPet.setMensajeTecnico("Error en searchPersona de la clase LoginDAO");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				st.close();
			} catch (Exception e) {
				/* ignored */ }
			try {
				pool.liberarConexion(conexion);
			} catch (Exception e) {
				/* ignored */ }
		}

	}
}