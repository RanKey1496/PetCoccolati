package com.petcoccolati.dao;

import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.util.ExceptionPet;

public interface NewServiceDAOInt {

	public void createServicio(NewServiceDTO service) throws ExceptionPet;
	
}
