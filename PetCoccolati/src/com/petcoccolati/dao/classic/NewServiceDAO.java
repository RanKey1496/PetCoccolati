package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.NewServiceDAOInt;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceDAO implements NewServiceDAOInt{
	
	private PoolConection pool;
	private Conection conection;
	
	private static final Logger logger = Logger.getLogger(NewServiceDAO.class);
	
	public NewServiceDAO() {
		conection = new Conection();
		pool = new PoolConection();
		logger.info("Se creó un NewServiceDAO");
	}
	
	public void createServicio(NewServiceDTO service) throws ExceptionPet{
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
			String query = "INSERT INTO Servicios (Fecha_inicio, Fecha_fin, Tipo, Personal_Cedula, Mascota_Id) values (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			
			ps.setString(1, service.getFechaInicio());
			ps.setString(2, service.getFechaFin());
			ps.setString(3, service.getTipo());
			ps.setInt(4, service.getPersonalCedula());
			ps.setInt(5, service.getMascotaId());
			logger.info("Servicio creado");
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			e.printStackTrace();
			excepPet.setMensajeUsuario("Error creado persona");
			excepPet.setMensajeTecnico("Error en crearPersona de la clase SigUpDAO (SQLException)");
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
	
}
