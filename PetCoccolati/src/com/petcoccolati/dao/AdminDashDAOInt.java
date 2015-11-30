package com.petcoccolati.dao;

import com.petcoccolati.dto.PersonalDTO;
import com.petcoccolati.util.ExceptionPet;

public interface AdminDashDAOInt {
	public void deletePersonal(String cedula) throws ExceptionPet;
	public void addPersonal(PersonalDTO personal) throws ExceptionPet;
}
