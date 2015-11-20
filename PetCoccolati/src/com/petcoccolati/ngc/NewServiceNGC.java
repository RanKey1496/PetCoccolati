package com.petcoccolati.ngc;

import com.petcoccolati.dao.NewServiceDAO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceNGC {
	
	public NewServiceDAO newServiceDao;
	public static NewServiceNGC newServiceNgc;
	
	public NewServiceNGC(){
		newServiceDao = new NewServiceDAO();
	}
	
	public static NewServiceNGC getInstance(){
		if (newServiceNgc == null) {
			newServiceNgc = new NewServiceNGC();
		}
		return newServiceNgc;		
	}

	public void crearServicio(NewServiceDTO newServiceDTO) throws ExceptionPet {
		newServiceDao.createServicio(newServiceDTO);		
	}

}
