package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

import com.petcoccolati.dto.PersonaDTO;

public class HomeCtl extends GenericForwardComposer{

	private Label userLabel;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public HomeCtl(){
		
	}
	
	public HomeCtl(PersonaDTO persona){
		
	}
	
}
