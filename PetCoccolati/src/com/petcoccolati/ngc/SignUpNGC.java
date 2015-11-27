package com.petcoccolati.ngc;

import org.apache.log4j.Logger;

import com.petcoccolati.dao.SignUpDAOInt;
import com.petcoccolati.dao.classic.SignUpDAO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class SignUpNGC {

	private SignUpDAOInt signupDAO;
	private static final Logger logger = Logger.getLogger(SignUpNGC.class);
	
	private SignUpNGC(){
		logger.info("Se creó SignUpNGC");
	}
	
	public void setSignupDAO(SignUpDAOInt signUpDAO){
		this.signupDAO = signUpDAO;
	}
	
	public void createPersona(PersonaDTO personaDTO) throws ExceptionPet {
		try{
			signupDAO.createPersona(personaDTO);
			logger.info("Entro a createPersona SignUpNGC");
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
	
	public void updatePersona(PersonaDTO personaDTO) throws ExceptionPet {
		try {
			signupDAO.updatePersona(personaDTO);
			logger.info("Entro a updatePersona SignUpNGC");
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
}
