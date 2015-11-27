package com.petcoccolati.controllers;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class NewPetCTL extends GenericForwardComposer{
	
	private Textbox name, dni, breed, species, genre, weight;
	private Button add;
	private PetNGC petNGC;
	private PersonaDTO usuario;
	
	private static final Logger logger = Logger.getLogger(NewPetCTL.class);
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		logger.info("Se creó NewPetCTL");
	}
	
	public NewPetCTL(){
	}
	
	public void onCreate(){
		usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
	}
	
	public void setPetNGC(PetNGC petNgc){
		this.petNGC = petNgc;
	}
	
	public void onClick$add(Event e) throws ExceptionPet{
		PetDTO pet = new PetDTO();
		pet.setDni(dni.getText());
		pet.setName(name.getText());
		pet.setGenre(genre.getText());
		pet.setBreed(breed.getText());
		pet.setSpecies(species.getText());
		pet.setWeight(Integer.parseInt(weight.getText()));
		pet.setCedula(usuario.getId());
		try {
			petNGC.crearPet(pet);
			logger.info("Se añadió una mascota");
		} catch (NumberFormatException e2) {
			e2.printStackTrace();
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
	}
}
