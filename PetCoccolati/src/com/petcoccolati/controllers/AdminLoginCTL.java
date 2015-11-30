package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.ngc.AdminLoginNGC;
import com.petcoccolati.ngc.LoginNGC;
import com.petcoccolati.util.ExceptionPet;

public class AdminLoginCTL extends GenericForwardComposer{
	
	Textbox txtId, txtPassword;
	Button btnSignin;
	private AdminLoginNGC adminLoginNGC;
	
	public AdminLoginCTL(){
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public void setAdminLoginNGC(AdminLoginNGC adminLoginNGC){
		this.adminLoginNGC = adminLoginNGC;
		System.out.println("Entro NGC");
	}
	
	public void onClick$btnSignin(Event e){
		String userId = txtId.getText();
		String userPass = txtPassword.getText();		
		try {
			boolean usuario = adminLoginNGC.verificarPersona(userId, userPass);
			if(usuario == true){
				Executions.getCurrent().getSession().setAttribute("Usuario", usuario);
				Executions.sendRedirect("portal.zul");
			}else{
				Messagebox.show("Email o contraseña incorrecto", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} catch (ExceptionPet e2) {
			Messagebox.show(e2.getMensajeUsuario());
			System.out.println(e2.getMensajeTecnico()); 
			System.out.println(e2.getExceptionOriginal().getMessage());
		}
	}

}
