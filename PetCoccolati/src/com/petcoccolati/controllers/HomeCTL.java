package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;

import com.petcoccolati.dto.PersonaDTO;

public class HomeCTL extends GenericForwardComposer{

	private Label userLabel;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public HomeCTL(){
		
	}
	
	public HomeCTL(PersonaDTO persona){
		
	}
	
	public void onCreate(){
		Execution execution = Executions.getCurrent();
		String name = execution.getParameter("Nombre");
		System.out.println(name);
		userLabel.setValue(name);
	}
	
}
