package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Include;

import com.petcoccolati.dto.PersonaDTO;

public class PortalCTL extends GenericForwardComposer implements PortalIntCTL{
	
	private Include innerIncld;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public PortalCTL(){
		
	}
	
	public void onCreate(){
		PersonaDTO usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
		if(usuario == null){
			Executions.sendRedirect("login.zul");
		}
		String link = execution.getParameter("section");
		if (link != null){
			innerIncld.setSrc(link+".zul");
		}
	}
	
	public void onClick$lblHome(Event e) {
		innerIncld.setSrc("home.zul");
	}
	public void onClick$lblMyPets(Event e) {
		innerIncld.setSrc("mypet.zul");
	}
	public void onClick$lblNewPet(Event e) {
		innerIncld.setSrc("newpet.zul");
	}
	public void onClick$lblNewService(Event e) {
		innerIncld.setSrc("newservice.zul");
	}
	public void onClick$lblHistory(Event e) {
		innerIncld.setSrc("history.zul");
	}
	public void onClick$lblSettings(Event e) {
		innerIncld.setSrc("settings.zul");
	}
	public void onClick$lblLogOut(Event e) {
		Executions.getCurrent().getSession().setAttribute("Usuario", null);
		Executions.sendRedirect("index.zul");
	}
}
