package com.petcoccolati.controllers;

import java.sql.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Timebox;

import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.ngc.NewServiceNGC;
import com.petcoccolati.util.ExceptionPet;

import javafx.scene.control.ComboBox;

public class NewServiceCTL extends GenericForwardComposer{
  	
	Combobox type, pet;
	Datebox date;
	Timebox time;
	
	NewServiceNGC newServiceNgc;
	
	public NewServiceCTL(){
		newServiceNgc = NewServiceNGC.getInstance();
	}
	
  	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

	}
  
  	public void onClick$request(Event e) throws ExceptionPet {
  		
  		NewServiceDTO newServiceDTO = new NewServiceDTO();
  		newServiceDTO.setMascotaId(5);  		
  		newServiceDTO.setPersonalCedula(1152210337);
  		newServiceDTO.setTipo(type.getText().toString());
  		newServiceDTO.setFechaInicio(date.getText());
  		newServiceDTO.setFechaFin(date.getText());
  		
  		newServiceNgc.crearServicio(newServiceDTO);
  	}
}
