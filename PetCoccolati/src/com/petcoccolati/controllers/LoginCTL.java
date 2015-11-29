package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.ngc.LoginNGC;
import com.petcoccolati.ngc.NewServiceNGC;
import com.petcoccolati.util.ExceptionPet;

public class LoginCTL extends GenericForwardComposer{

	private Textbox txtEmail;
	private Textbox txtPassword;
	private Button btnSignin;
	private Button btnCreate;
	
	private LoginNGC personaNGC;
	
	public LoginCTL(){
		
	}
	
	public void setLoginNGC(LoginNGC personaNGC){
		this.personaNGC = personaNGC;
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	}
	
	public void onCreate() {
		 
	}	
	
	public void onClick$btnSignin(Event e) {
		PersonaDTO persona = new PersonaDTO();
		persona.setEmail(txtEmail.getText());
		persona.setPassword(txtPassword.getText());
		try {
			PersonaDTO usuario = personaNGC.verificarPersona(persona);
			if(usuario != null){
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
	
	public void onClick$btnCreate(Event e) {
		Executions.sendRedirect("signup.zul");
	}
	
}
