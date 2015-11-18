package com.petcoccolati.ngc;

import com.petcoccolati.dao.SignUpDAO;
import com.petcoccolati.dto.LoginDTO;
import com.petcoccolati.dto.SignUpDTO;
import com.petcoccolati.util.ExceptionPet;

public class LoginNGC {
	private LoginDAO loginDAO;
	
	private static LoginNGC loginNGC;
	
	private LoginNGC(){
		loginDAO = new LoginDAO();
	}
	
	public static SignUpNGC getIntance(){
		if(loginNGC == null){
			loginNGC = new LoginNGC();
		}
		return loginNGC;
	}
	
	public void verificarPersona(LoginDTO personaDTO) throws ExceptionPet {
		try{
			
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
	

}
