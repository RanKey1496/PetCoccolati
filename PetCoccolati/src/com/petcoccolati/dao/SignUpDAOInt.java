package com.petcoccolati.dao;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.util.ExceptionPet;

public interface SignUpDAOInt {

	public void createPersona(PersonaDTO persona) throws ExceptionPet;
	public void searchPersona(PersonaDTO personaDTO) throws ExceptionPet;
	
}
