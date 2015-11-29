package com.petcoccolati.dao;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public interface LoginDAOInt {
	
	public PersonaDTO searchPersona(PersonaDTO persona) throws ExceptionPet;
	
}
