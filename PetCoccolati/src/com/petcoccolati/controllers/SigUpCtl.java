package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.SignUpDTO;
import com.petcoccolati.ngc.SignUpNGC;
import com.petcoccolati.util.ExceptionPet;

public class SigUpCtl extends GenericForwardComposer {

	private Textbox txtId;
	private Textbox txtFirst;
	private Textbox txtLast;
	private Textbox txtPhone;
	private Textbox txtEmail;
	private Textbox txtPassword;
	private Button btnCreate;
	private Button btnSignin;
	
	private SignUpNGC signupNgc;
	
	public SigUpCtl(){
		signupNgc = SignUpNGC.getIntance();
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onCreate() {

	}

	public void onClick$btnCreate(Event e) {
		SignUpDTO persona = new SignUpDTO();
		persona.setId(Integer.parseInt(txtId.getText()));
		persona.setFirst(txtFirst.getText());
		persona.setLast(txtLast.getText());
		persona.setPhone(txtPhone.getText());
		persona.setEmail(txtEmail.getText());
		persona.setPassword(txtPassword.getText());
		try {
			signupNgc.createPersona(persona);
		} catch (ExceptionPet e2) {
			Messagebox.show(e2.getMensajeUsuario());
			System.out.println(e2.getMensajeTecnico()); 
		}
	}
	
	public void onClick$btnSignin(Event e){
		Executions.sendRedirect("login.zul");
	}
}
