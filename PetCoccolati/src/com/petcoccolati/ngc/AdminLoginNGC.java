package com.petcoccolati.ngc;

import com.petcoccolati.dao.AdminLoginDAOInt;
import com.petcoccolati.dao.LoginDAOInt;
import com.petcoccolati.util.ExceptionPet;

public class AdminLoginNGC {
	
	AdminLoginDAOInt adminLoginDAO;
	
	public AdminLoginNGC(){
		
	}

	public void setAdminLoginDAO(AdminLoginDAOInt adminLoginDAO){
		this.adminLoginDAO = adminLoginDAO;
		System.out.println("Entro DAO");
	}
	
	public boolean verificarPersona(String userId, String userPass) throws ExceptionPet{
		return adminLoginDAO.exists(userId, userPass);
	}

}
