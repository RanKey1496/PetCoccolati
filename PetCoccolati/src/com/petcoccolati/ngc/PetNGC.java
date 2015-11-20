package com.petcoccolati.ngc;

import com.petcoccolati.dao.PetDAO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public class PetNGC {
	
	private PetDAO petDao;	
	private static PetNGC petNGC;
	
	private PetNGC(){
		petDao = new PetDAO();
	}
	
	public static PetNGC getIntance(){
		if(petNGC == null){
			petNGC = new PetNGC();
		}
		return petNGC;
	}
	
	public void crearPet(PetDTO pet) throws ExceptionPet {
		try{
			petDao.createPet(pet);
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
	
	public void listaPets(PersonaDTO persona){
		petDao.listaPets(persona);
	}
 
}
