package com.petcoccolati.dao;

import java.util.List;

import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.util.ExceptionPet;

public interface PetDAOInt {

	public void createPet(PetDTO pet) throws ExceptionPet;
	public List<PetDTO> listaPets(String cedula) throws ExceptionPet;
	public List<String> listNombrePets(String cedula) throws ExceptionPet;
	public void borrarPet(PetDTO pet) throws ExceptionPet;
}
