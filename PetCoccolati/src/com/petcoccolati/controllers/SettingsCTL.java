package com.petcoccolati.controllers;

import java.io.IOException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.ngc.SignUpNGC;
import com.petcoccolati.util.ExceptionPet;

public class SettingsCTL extends GenericForwardComposer {

	private Textbox txtChangeName;
	private Textbox txtChangePassword;
	private Textbox txtChangeEmail;
	private Button btnSave;
	private Radio English;
	private Radio Spanish;
	
	private String lenguaje;

	private PersonaDTO usuario;

	private SignUpNGC signupNgc;

	private static final Logger logger = Logger.getLogger(SettingsCTL.class);

	public void setSignupNgc(SignUpNGC sigupNgc) {
		this.signupNgc = sigupNgc;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		logger.info("Se creó SettingsCTL");
	}

	public void onClick$btnSave(Event e){
		checkR();
		PersonaDTO persona = new PersonaDTO();
		persona.setFirst(txtChangeName.getText());
		persona.setPassword(txtChangePassword.getText());
		persona.setEmail(txtChangeEmail.getText());
		persona.setId(usuario.getId());
		
		try {
			Executions.getCurrent().setAttribute("lang", lenguaje);
			signupNgc.updatePersona(persona);
			logger.info("Se actualizó una persona");
			Executions.sendRedirect("");
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
	}

	public void onCreate() {
		usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
	}

	public void checkR(){
		if (English.isChecked()) {
		         lenguaje = "en";
		         Locale preferredLocale = org.zkoss.util.Locales.getLocale(lenguaje);
		 		session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, preferredLocale);
		} else if (Spanish.isChecked()) {
					lenguaje = "es";
					Locale preferredLocale = org.zkoss.util.Locales.getLocale(lenguaje);
			 		session.setAttribute(org.zkoss.web.Attributes.PREFERRED_LOCALE, preferredLocale);
		}
	}
}
