package com.petcoccolati.ngc;

import com.petcoccolati.dao.SignUpDAO;
import com.petcoccolati.dto.SignUpDTO;
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
	
	public void createPersona(SignUpDTO personaDTO) throws ExceptionPet {
		try{
			signupDAO.createPersona(personaDTO);
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
}
