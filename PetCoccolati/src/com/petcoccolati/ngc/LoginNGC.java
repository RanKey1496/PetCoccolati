package com.petcoccolati.ngc;

import com.petcoccolati.dao.LoginDAO;
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
	
	public static LoginNGC getIntance(){
		if(loginNGC == null){
			loginNGC = new LoginNGC();
		}
		return loginNGC;
	}
	
	public Boolean verificarPersona(LoginDTO persona) throws ExceptionPet {
		try{
			if(loginDAO.searchPersona(persona)){
				return true;
			}
			else{
				return false;
			}
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
	

}
