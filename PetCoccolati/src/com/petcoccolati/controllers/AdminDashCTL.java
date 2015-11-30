package com.petcoccolati.controllers;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonalDTO;
import com.petcoccolati.ngc.AdminDashNGC;
import com.petcoccolati.util.ExceptionPet;

public class AdminDashCTL extends GenericForwardComposer{

	AdminDashNGC adminDashNGC;
	Textbox txtId, txtNombre, txtApellido, txtContrasena, txtDireccion, txtTelefono, txtFireId;
	
	public AdminDashCTL(){
		
	}
	
	public void setAdminDashNGC(AdminDashNGC adminDashNGC){
		this.adminDashNGC = adminDashNGC;		
	}	
	
	public void onClick$btnSave(Event e) throws ExceptionPet{
		PersonalDTO personal = new PersonalDTO();
		personal.setPhone(txtTelefono.getText());
		personal.setName(txtNombre.getText());
		personal.setLastName(txtApellido.getText());
		personal.setPass(txtContrasena.getText());
		personal.setAddress(txtDireccion.getText());
		personal.setId(Integer.valueOf(txtId.getText()));
		adminDashNGC.addPersonal(personal);
	}
	
	public void onClick$btnFire(Event e) throws ExceptionPet{
		String id = txtFireId.getText();
		adminDashNGC.deletePersonal(id);
	}
}
