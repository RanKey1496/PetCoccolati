package com.petcoccolati.ngc;

import com.petcoccolati.dao.LoginDAO;
import com.petcoccolati.dao.PetDAO;
import com.petcoccolati.dto.LoginDTO;
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
	
	public void crearPersona(PetDTO pet) throws ExceptionPet {
		petDao.createPet(pet);	
	}

}
