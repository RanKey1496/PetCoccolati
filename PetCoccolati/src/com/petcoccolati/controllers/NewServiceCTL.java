package com.petcoccolati.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.CombboxListModelConverter;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModels;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Timebox;

import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.NewServiceNGC;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceCTL extends GenericForwardComposer{
  	
	private Combobox type, pet;
	private Datebox date;
	private Timebox time;
	private PetNGC petNGC;
	private PersonaDTO usuario;
	private int mascotaid;
	
	private static final Logger logger = Logger.getLogger(NewServiceCTL.class);
	
	private NewServiceNGC newServiceNgc;

	private List<PetDTO> listPet;
	private List<String> listNombrePet;
	
	public NewServiceCTL(){
	}
	
	public void onCreate(){
		usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
		definirModelo();
		loadComboboxPet();
	}
	
	public void setNewServiceNgc(NewServiceNGC newServiceNgc){
		this.newServiceNgc = newServiceNgc;
	}
	
	public void setPetNGC(PetNGC petNgc){
		this.petNGC = petNgc;
	}
	
  	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		logger.info("Se creó NewServiceCTL");
	}
  
  	public void onClick$request(Event e) throws ExceptionPet {
  		loadMascotaId();
  		NewServiceDTO newServiceDTO = new NewServiceDTO();
  		newServiceDTO.setMascotaId(mascotaid);  		
  		newServiceDTO.setPersonalCedula(1152210337);
  		newServiceDTO.setTipo(type.getText().toString());
  		newServiceDTO.setFechaInicio(date.getText());
  		newServiceDTO.setFechaFin(date.getText());
  		
  		try {
  			newServiceNgc.crearServicio(newServiceDTO);
  			logger.info("Se añadió un servicio");
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
  		
  	}
  	
  	private void definirModelo() {
  		listNombrePet = null;
  		listPet = null;
		try {
			listNombrePet = petNGC.listaNombrePets(usuario.getId());
			listPet = petNGC.listaPets(usuario.getId());
			logger.info("Cargó la lista de Mascotas");
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
	}
  	
  	private void loadComboboxPet(){
  		try {
			ListModelList model = new ListModelList(listNombrePet);
			pet.setModel(model);
			pet.setSelectedIndex(0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	}
  	
  	private void loadMascotaId(){
  		mascotaid = listPet.get(pet.getSelectedIndex()).getId();
  	}
}
