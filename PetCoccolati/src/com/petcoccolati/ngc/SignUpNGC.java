package com.petcoccolati.ngc;

import com.petcoccolati.dao.SignUpDAO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class SignUpNGC {

	private SignUpDAO signupDAO;
	
	private static SignUpNGC signupNGC;
	
	private SignUpNGC(){
		signupDAO = new SignUpDAO();
	}
	
	public static SignUpNGC getIntance(){
		if(signupNGC == null){
			signupNGC = new SignUpNGC();
		}
		return signupNGC;
	}
	
	public void createPersona(PersonaDTO personaDTO) throws ExceptionPet {
		try{
			signupDAO.createPersona(personaDTO);
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
}
