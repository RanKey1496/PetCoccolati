package com.petcoccolati.ngc;

import org.apache.log4j.Logger;

import com.petcoccolati.dao.NewServiceDAOInt;
import com.petcoccolati.dao.classic.NewServiceDAO;
import com.petcoccolati.dto.NewServiceDTO;
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

	public void crearServicio(NewServiceDTO newServiceDTO) throws ExceptionPet {
		try {
			newServiceDao.createServicio(newServiceDTO);
			logger.info("Entro a crearServicio NewServiceNGC");
		} catch (ExceptionPet e) {
			throw e;
		}
	}

}