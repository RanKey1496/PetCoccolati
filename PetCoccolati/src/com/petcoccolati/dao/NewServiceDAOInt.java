package com.petcoccolati.dao;

import com.petcoccolati.dto.NewServiceDTO;
import java.util.List;
import com.petcoccolati.util.ExceptionPet;


public interface NewServiceDAOInt {

	public void createServicio(NewServiceDTO service) throws ExceptionPet;
	public List<NewServiceDTO> listaServicios(String cedula) throws ExceptionPet;
	
}
