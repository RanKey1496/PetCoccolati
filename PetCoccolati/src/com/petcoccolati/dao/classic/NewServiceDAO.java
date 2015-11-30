package com.petcoccolati.dao.classic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.petcoccolati.bd.Conection;
import com.petcoccolati.bd.PoolConection;
import com.petcoccolati.dao.NewServiceDAOInt;
import com.petcoccolati.dto.DetalleDTO;
import com.petcoccolati.dto.FacturaDTO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.dto.ServicioDTO;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceDAO implements NewServiceDAOInt{
	
	private PoolConection pool;
	List<NewServiceDTO> serviceList = new ArrayList<>();
	private Conection conection;
	
	private static final Logger logger = Logger.getLogger(NewServiceDAO.class);
	
	public NewServiceDAO() {
		conection = new Conection();
		pool = new PoolConection();
		logger.info("Se creó un NewServiceDAO");
	}
	
	public void createDetalle(DetalleDTO detalle) throws ExceptionPet{
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
			String query = "INSERT INTO Detalle_Servicios (Factura_Id, Id, Servicio_Id, Personal_Cedula, Descripcion, Fecha_Inicio, Fecha_Fin, Precio) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			
			ps.setInt(1, detalle.getFacturaid());
			ps.setInt(2, detalle.getId());
			ps.setInt(3, detalle.getServicioid());
			ps.setInt(4, detalle.getPersonalcedula());
			ps.setString(5, detalle.getDescripcion());
			ps.setDate(6, (Date) detalle.getFechainicio());
			ps.setDate(7, (Date) detalle.getFechafin());
			ps.setDouble(8, detalle.getPrecio());
			ps.executeUpdate();	
			logger.info("Detalle creado");
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			e.printStackTrace();
			excepPet.setMensajeUsuario("Error creando detalle");
			excepPet.setMensajeTecnico("Error en createDetalle de la clase NewServiceDAO (SQLException)");
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creando detalle");
			excepPet.setMensajeTecnico("Error en createDetalle de la clase NewServiceDAO");
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
	public void createFactura(FacturaDTO factura) throws ExceptionPet {
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
			String query = "INSERT INTO Facturas (Fecha, Mascota_Id) values (?, ?)";
			ps = conn.prepareStatement(query);
			
			ps.setDate(1, (Date) factura.getFecha());
			ps.setInt(2, factura.getMascota_id());
			logger.info("Factura creada");
			ps.executeUpdate();			
			
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			e.printStackTrace();
			excepPet.setMensajeUsuario("Error creado factura");
			excepPet.setMensajeTecnico("Error en createFactura de la clase NewServiceDAO (SQLException)");
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error creado factura");
			excepPet.setMensajeTecnico("Error en createFactura de la clase NewServiceDAO");
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
	public int lastIdFactura() throws ExceptionPet {
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
			String query = "SELECT Id FROM Facturas ORDER BY Id DESC LIMIT 1";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return rs.getInt("Id");
			}
		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			e.printStackTrace();
			excepPet.setMensajeUsuario("Error cargando la ultima factura");
			excepPet.setMensajeTecnico("Error en lastIdFactura de la clase NewServiceDAO (SQLException)");
			throw excepPet;
		} catch(Exception e){
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error cargando la ultima factura");
			excepPet.setMensajeTecnico("Error en lastIdFactura de la clase NewServiceDAO");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		}finally{
			try { rs.close(); } catch (Exception e) { /* ignored */ }
		    try { st.close(); } catch (Exception e) { /* ignored */ }
		    try {
		    	pool.liberarConexion(conexion);
		    } catch (Exception e) { /* ignored */ }
			
		}
		return -1;
	}
	
	public List<ServicioDTO> listaServicios() throws ExceptionPet {

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
			String query = "SELECT * FROM Servicios";
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();

			List<ServicioDTO> serviceList = new ArrayList<>();
			
			while (rs.next()) {
				ServicioDTO servicio = new ServicioDTO();
				servicio.setId(rs.getInt("Id"));
				servicio.setTipo(rs.getString("Tipo"));
				servicio.setDescripcion(rs.getString("Descripcion"));
				serviceList.add(servicio);
				logger.info("Añadió la lista de Pets");
			}

			logger.info("Retornó la lista de Servicios");
			return serviceList;

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

	public List<String> listNombreType() throws ExceptionPet{

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
			String query = "SELECT Tipo FROM Servicios";
			ps = conn.prepareStatement(query);
			
			rs = ps.executeQuery();

			List<String> serviceList = new ArrayList<>();
			
			while (rs.next()) {
				serviceList.add(rs.getString("Tipo"));
				logger.info("Añadió la lista de nombres de los Servicios disponibles");
			}

			logger.info("Retornó la lista de nombres de los Servicios disponibles");
			return serviceList;

		} catch (SQLException e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error retornando la lista de servicios");
			excepPet.setMensajeTecnico("Error en listNombreType (SQLException)");
			excepPet.setExceptionOriginal(e);
			throw excepPet;
		} catch (Exception e) {
			ExceptionPet excepPet = new ExceptionPet();
			excepPet.setMensajeUsuario("Error retornando la lista de servicios");
			excepPet.setMensajeTecnico("Error en listNombreType de la clase LoginDAO");
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
