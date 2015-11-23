package com.petcoccolati.dao;

import java.util.List;

import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public interface PetDAOInt {

	public void createPet(PetDTO pet) throws ExceptionPet;
	public List<PetDTO> listaPets() throws ExceptionPet;
	
}
