package com.petcoccolati.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class NewPetCtl extends GenericForwardComposer{
	
	private Textbox name, dni, breed, species, genre, weight;
	private Button add;
	PetNGC petNgc;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public NewPetCtl(){
		petNgc = PetNGC.getIntance();
	}
	
	public NewPetCtl(PetDTO pet){
		
	}
	
	public void onClick$add(Event e) throws ExceptionPet{
		
		if (!("").equals(dni.getText()) && !("").equals(name.getText()) && !("").equals(genre.getText())
				&& !("").equals(breed.getText()) && !("").equals(species.getText())
				&& !("").equals(weight.getText())) {
		PetDTO pet = new PetDTO();
		pet.setDni(Integer.parseInt(dni.getText()));
		pet.setName(name.getText());
		pet.setGenre(genre.getText());
		pet.setBreed(breed.getText());
		pet.setSpecies(species.getText());
		pet.setWeight(Integer.parseInt(weight.getText()));
		try {
		petNgc.crearPet(pet);
		} catch (ExceptionPet e2) {
			Messagebox.show(e2.getMensajeUsuario());
			System.out.println(e2.getMensajeTecnico());
		}
	}else {
		Messagebox.show("Por favor ingrese todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
	}
	}
	public void onCreate(){
		
	}
}
