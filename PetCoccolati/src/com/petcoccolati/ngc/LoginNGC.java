package com.petcoccolati.ngc;

import com.petcoccolati.dao.LoginDAOInt;
import com.petcoccolati.dao.classic.LoginDAO;
import com.petcoccolati.dao.classic.SignUpDAO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public class LoginNGC {
	
	private LoginDAOInt loginDAO;
	
	private LoginNGC(){
	}
	
	public void setLoginDAO(LoginDAOInt loginDAO){
		this.loginDAO = loginDAO;
	}
	
	public PersonaDTO verificarPersona(PersonaDTO persona) throws ExceptionPet {
		try{
			PersonaDTO usuario = loginDAO.searchPersona(persona);
			if(usuario != null){
				return usuario;
			}
			else{
				return null;
			}
		}catch(ExceptionPet eWeb){
			throw eWeb;
		}
	}
	

}
