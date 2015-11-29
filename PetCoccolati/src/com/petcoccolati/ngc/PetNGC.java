package com.petcoccolati.ngc;

import java.util.List;

import org.apache.log4j.Logger;

import com.petcoccolati.dao.PetDAOInt;
import com.petcoccolati.dao.classic.PetDAO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public class PetNGC {

	private PetDAOInt petDAO;
	private static final Logger logger = Logger.getLogger(PetNGC.class);
	
	private PetNGC() {
		logger.info("Se creó PetNGC");
	}
	
	public void setPetDAO(PetDAOInt petDao){
		this.petDAO = petDao;
	}

	public void crearPet(PetDTO pet) throws ExceptionPet {
		try {
			petDAO.createPet(pet);
			logger.info("Entro a crearPet PetNGC");
		} catch (ExceptionPet ePet) {
			throw ePet;
		}
	}

	public List<PetDTO> listaPets(String cedula) throws ExceptionPet {
		logger.info("Entro a listaPets PetNGC");
		return petDAO.listaPets(cedula);
	}
	
	public List<String> listaNombrePets(String cedula) throws ExceptionPet{
		logger.info("Entro a listaPets PetNGC");
		return petDAO.listNombrePets(cedula);
	}

	public void borrarPet(PetDTO pet) throws ExceptionPet{
		try {
			logger.info("Entro a borrarPet PetNGC");
			petDAO.borrarPet(pet);
		} catch (ExceptionPet e) {
			throw e;
		}
	}
}