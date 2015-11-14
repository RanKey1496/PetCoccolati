package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;

public class Login extends GenericForwardComposer{

	private Textbox txtEmail;
	private Textbox txtPassword;
	private Button btnSignin;
	private Button btnCreate;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public void onCreate() {
		 
	}	
	
}
