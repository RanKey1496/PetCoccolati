package com.petcoccolati.dao;

import com.petcoccolati.dto.LoginDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public interface LoginDAOInt {
	
	public PersonaDTO searchPersona(LoginDTO persona) throws ExceptionPet;
	
}
