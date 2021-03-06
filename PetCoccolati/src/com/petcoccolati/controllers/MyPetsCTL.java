package com.petcoccolati.controllers;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.acegi.ShowWindowEventListener;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class MyPetsCTL extends GenericForwardComposer implements ListitemRenderer {

	private Listbox lsxPet;
	private PetNGC petNGC;
	private PersonaDTO usuario;
	private ListModel model;
	private PetDTO pet;
	private static final Logger logger = Logger.getLogger(MyPetsCTL.class);

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	private Window win;
	
	public MyPetsCTL() {
	}
	
	public void setPetNGC(PetNGC petNgc){
		this.petNGC = petNgc;
	}

	public void onCreate() {
		usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
		definirModelo();
	}

	private void definirModelo() {
		List<PetDTO> listaPet = null;
		try {
			listaPet = petNGC.listaPets(usuario.getId());
			logger.info("Carg� la lista de Mascotas");
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
		model = new ListModelList(listaPet);
		lsxPet.setModel(model);
		lsxPet.setItemRenderer(this);
	}

	@Override
	public void render(Listitem arg0, Object arg1, int arg2) throws Exception {
		PetDTO pet = (PetDTO) arg1;
		Listcell lcNombre = new Listcell(pet.getName());
		Listcell lcGenero = new Listcell(pet.getGenre());
		Listcell lcPeso = new Listcell(String.valueOf(pet.getWeight()));
		Listcell lcRaza = new Listcell(pet.getBreed());
		Listcell lcEspecie = new Listcell(pet.getSpecies());
		Listcell lcDNI = new Listcell(String.valueOf(pet.getDni()));
		arg0.appendChild(lcNombre);
		arg0.appendChild(lcGenero);
		arg0.appendChild(lcPeso);
		arg0.appendChild(lcRaza);
		arg0.appendChild(lcEspecie);
		arg0.appendChild(lcDNI);
	}

	public void onSelect$lsxPet(Event e) throws ExceptionPet{
		pet = (PetDTO) model.getElementAt(lsxPet.getSelectedIndex());
	}
	
	public void onClick$btnMyPetDelete(Event e) throws ExceptionPet{
		try {
			petNGC.borrarPet(pet);
			Messagebox.show(pet.getName() + " ya no es tu mascota :(", "Informaci�n", Messagebox.OK, Messagebox.EXCLAMATION, new org.zkoss.zk.ui.event.EventListener() {
			    public void onEvent(Event evt) throws InterruptedException {
			        if (evt.getName().equals("onOK")) {
			        	Executions.sendRedirect("portal.zul?section=mypet");
			        }
			    }
			});
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
	}
	
	public void onClick$btnMyPetAdd(Event e){
		Executions.sendRedirect("portal.zul?section=newpet");
	}
}