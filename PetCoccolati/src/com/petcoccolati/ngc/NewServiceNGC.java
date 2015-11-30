package com.petcoccolati.ngc;

import java.util.List;

import org.apache.log4j.Logger;

import com.petcoccolati.dao.NewServiceDAOInt;
import com.petcoccolati.dao.classic.NewServiceDAO;
import com.petcoccolati.dto.DetalleDTO;
import com.petcoccolati.dto.FacturaDTO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.dto.ServicioDTO;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceNGC {
	
	private NewServiceDAOInt newServiceDao;
	private static final Logger logger = Logger.getLogger(NewServiceNGC.class);
	
	public void setNewServiceDao(NewServiceDAOInt newServiceDao){
		this.newServiceDao = newServiceDao;
	}
	
	public NewServiceNGC(){
		logger.info("Se creó NewServiceNGC");
	}

	public void crearDetalle(DetalleDTO detalleDTO) throws ExceptionPet {
		try {
			newServiceDao.createDetalle(detalleDTO);
			logger.info("Entro a crearDetalle NewServiceNGC");
		} catch (ExceptionPet e) {
			throw e;
		}
	}
	
	public void crearFactura(FacturaDTO factura) throws ExceptionPet{
		try {
			newServiceDao.createFactura(factura);
			logger.info("Entro a crearFactura NewServiceNGC");
		} catch (ExceptionPet e) {
			throw e;
		}
	}

	public int lastIdFactura() throws ExceptionPet{
		try {
			logger.info("Entro a lastIdFactura NewServiceNGC");
			return newServiceDao.lastIdFactura();
		} catch (ExceptionPet e) {
			throw e;
		}
	}

	public List<ServicioDTO> listaServicios(String userId) throws ExceptionPet{
		logger.info("Entro a listaPets PetNGC");
		return newServiceDao.listaServicios(userId);
	}
	
	public List<String> listaNombreType() throws ExceptionPet{
		logger.info("Entro a listaServicios NewServiceNGC");
		return newServiceDao.listNombreType();
	}
}