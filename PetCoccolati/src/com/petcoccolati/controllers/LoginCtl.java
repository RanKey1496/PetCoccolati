package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.LoginDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.ngc.LoginNGC;
import com.petcoccolati.util.ExceptionPet;

public class LoginCtl extends GenericForwardComposer{

	private Textbox txtEmail;
	private Textbox txtPassword;
	private Button btnSignin;
	private Button btnCreate;
	
	private LoginNGC loginNGC;
	
	public LoginCtl(){
		loginNGC = LoginNGC.getIntance();
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	}
	
	public void onCreate() {
		 
	}	
	
	public void onClick$btnSignin(Event e) {
		LoginDTO persona = new LoginDTO();
		persona.setEmail(txtEmail.getText());
		persona.setPassword(txtPassword.getText());
		try {
			PersonaDTO usuario = loginNGC.verificarPersona(persona);
			if(usuario != null){
				Executions.sendRedirect("home.zul?Nombre="+usuario.getId());
			}else{
				Messagebox.show("El usuario no existe");
			}
		} catch (ExceptionPet e2) {
			Messagebox.show(e2.getMensajeUsuario());
			System.out.println(e2.getMensajeTecnico()); 
			System.out.println(e2.getExceptionOriginal().getMessage());
		}
	}
	
}
