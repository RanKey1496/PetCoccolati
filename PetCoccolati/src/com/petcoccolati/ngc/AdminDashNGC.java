package com.petcoccolati.ngc;

import com.petcoccolati.dao.AdminDashDAOInt;
import com.petcoccolati.dao.AdminLoginDAOInt;
import com.petcoccolati.dto.PersonalDTO;
import com.petcoccolati.util.ExceptionPet;

public class AdminDashNGC {
	
	public AdminDashDAOInt adminDashDAO;
	
	public void setAdminDashDAO(AdminDashDAOInt adminDashDAO){
		this.adminDashDAO = adminDashDAO;
		System.out.println("Entro DAO");
	}

	public void addPersonal(PersonalDTO personal) throws ExceptionPet {
		adminDashDAO.addPersonal(personal);
	}
	
	public void deletePersonal(String id) throws ExceptionPet {
		adminDashDAO.deletePersonal(id);
	}

}
