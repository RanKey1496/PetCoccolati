package com.petcoccolati.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class MyPetsCTL extends GenericForwardComposer implements ListitemRenderer {

	private Listbox lsxPet;
	private PetNGC petNGC;
	private PersonaDTO usuario;
	private static final Logger logger = Logger.getLogger(MyPetsCTL.class);

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

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
			logger.info("Cargó la lista de Mascotas");
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
		ListModel model = new ListModelList(listaPet);
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
}