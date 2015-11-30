package com.petcoccolati.dao;

import com.petcoccolati.util.ExceptionPet;

public interface AdminLoginDAOInt {
	public boolean exists(String id, String password) throws ExceptionPet;
}
