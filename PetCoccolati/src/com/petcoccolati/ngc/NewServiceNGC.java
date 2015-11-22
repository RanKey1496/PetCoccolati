package com.petcoccolati.ngc;

import com.petcoccolati.dao.NewServiceDAOInt;
import com.petcoccolati.dao.classic.NewServiceDAO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceNGC {
	
	private NewServiceDAOInt newServiceDao;
	public static NewServiceNGC newServiceNgc;
	
	public void setNewServiceDao(NewServiceDAOInt newServiceDao){
		this.newServiceDao = newServiceDao;
	}
	
	public NewServiceNGC(){
		//newServiceDao = new NewServiceDAO();
	}
	
	/*public static NewServiceNGC getInstance(){
		if (newServiceNgc == null) {
			newServiceNgc = new NewServiceNGC();
		}
		return newServiceNgc;		
	}*/

	public void crearServicio(NewServiceDTO newServiceDTO) throws ExceptionPet {
		newServiceDao.createServicio(newServiceDTO);		
	}

}