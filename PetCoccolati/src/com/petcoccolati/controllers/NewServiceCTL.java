package com.petcoccolati.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.petcoccolati.dto.DetalleDTO;
import com.petcoccolati.dto.FacturaDTO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PersonaDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.dto.ServicioDTO;
import com.petcoccolati.ngc.NewServiceNGC;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class NewServiceCTL extends GenericForwardComposer{
  	
	private Combobox type, pet;
	private Datebox date, dateend;
	private PetNGC petNGC;
	private PersonaDTO usuario;
	private FacturaDTO factura;
	private int mascotaid;
	private int servicioid;
	private int lastIdFactura;
	
	private static final Logger logger = Logger.getLogger(NewServiceCTL.class);
	
	private NewServiceNGC newServiceNgc;

	private List<PetDTO> listPet;
	private List<ServicioDTO> listType;
	private List<String> listNombrePet;
	private List<String> listNombreType;
	
	public NewServiceCTL(){
	}
	
	public void onCreate(){
		usuario = (PersonaDTO) Executions.getCurrent().getSession().getAttribute("Usuario");
		definirModelo();
		loadComboboxPet();
		loadFacturaId();
		loadComboboxType();
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
  
  	public void onClick$request(Event e) throws ExceptionPet, ParseException {
  		loadMascotaId();
  		loadServicioId();
  		
  		//Arreglar esta mierda
  		String valores = date.getValue().toString();
  		String valoresend = dateend.getValue().toString();
  		Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valores);
  		Date fechaend = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(valoresend);
  		Date dateactual = new Date();
  		
  		long diferencia = fechaend.getTime() - fecha.getTime();
  		double precio = diferencia/0.00416;
  		
  		FacturaDTO factura = new FacturaDTO();
  		factura.setId(lastIdFactura+1);
  		factura.setFecha(dateactual);
  		factura.setMascota_id(mascotaid);
  		
  		DetalleDTO detalle = new DetalleDTO();
  		detalle.setFacturaid(lastIdFactura+1);
  		detalle.setId(1);
  		detalle.setServicioid(servicioid);
  		detalle.setPersonalcedula(95845414);
  		detalle.setDescripcion("Sin contratiempos");
  		detalle.setFechainicio(fecha);
  		detalle.setFechafin(fechaend);
  		detalle.setPrecio(precio);
  		  		
  		try {
  			newServiceNgc.crearFactura(factura);
  			newServiceNgc.crearDetalle(detalle);
  			logger.info("Se añadió un servicio");
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
  	}
  	
  	private void definirModelo() {
  		listNombrePet = null;
  		listPet = null;
  		listNombreType = null;
  		listType = null;
		try {
			listNombrePet = petNGC.listaNombrePets(usuario.getId());
			listPet = petNGC.listaPets(usuario.getId());
			listNombreType = newServiceNgc.listaNombreType();
			listType = newServiceNgc.listaServicios();
			logger.info("Cargó la lista de Mascotas");
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
	}
  	
  	private void loadComboboxPet(){
  		try {
			ListModelList model = new ListModelList(listNombrePet);
			pet.setModel(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	}
  	
  	private void loadMascotaId(){
  		mascotaid = listPet.get(pet.getSelectedIndex()).getId();
  	}

  	private void loadFacturaId(){
  		try {
			lastIdFactura = newServiceNgc.lastIdFactura();
			logger.info("Se cargó el id de la ultima factura");
		} catch (ExceptionPet e1) {
			Messagebox.show(e1.getMensajeUsuario());
			e1.pintarErrorLog(e1.getMensajeTecnico());
		}
  	}

  	private void loadComboboxType(){
  		try {
			ListModelList model = new ListModelList(listNombreType);
			type.setModel(model);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
  	}

  	private void loadServicioId(){
  		servicioid = listType.get(type.getSelectedIndex()).getId();
  	}
}
