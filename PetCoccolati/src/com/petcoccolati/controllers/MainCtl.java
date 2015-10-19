package com.petcoccolati.controllers;

import java.io.IOException;
import java.util.HashMap;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;

public class MainCtl extends GenericForwardComposer implements ListitemRenderer{

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	@Override
	public void render(Listitem arg0, Object arg1, int arg2) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	public void onCreate() {
		 
	}	
	
	/*public void onClick$btnLogin(Event ev) {
		java.io.InputStream zulInput = this.getClass().getClassLoader().getResourceAsStream("com/petcoccolati/views/login.zul") ;
		java.io.Reader zulReader = new java.io.InputStreamReader(zulInput);
		try {
			Component c = Executions.createComponentsDirectly(zulReader,"zul",null,new HashMap<String, Object>()) ;
			Window win = (Window)c;
			win.doModal();
			System.out.println("despues del do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}*/
	
}
