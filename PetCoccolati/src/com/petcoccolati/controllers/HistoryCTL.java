package com.petcoccolati.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.petcoccolati.dao.classic.NewServiceDAO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.HistoryNGC;
import com.petcoccolati.ngc.NewServiceNGC;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class HistoryCTL extends GenericForwardComposer implements ListitemRenderer{

	private Listbox lbHistory;
	private static final Logger logger = Logger.getLogger(MyPetsCTL.class);
	private NewServiceNGC newServiceNgc;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		logger.info("Se creó MyPetsCTL");
	}

	public HistoryCTL() {
	}
	
	public void setNewServiceNgc(NewServiceNGC newServiceNgc){
		this.newServiceNgc = newServiceNgc;
	}

	public void onCreate() {
		definirModelo();
	}

	private void definirModelo() {
		List<NewServiceDTO> historyList = null;
		try {
			historyList = newServiceNgc.listaServicios(5);
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
		ListModel model = new ListModelList(historyList);
		lbHistory.setModel(model);
		lbHistory.setItemRenderer(this);
	}

	@Override
	public void render(Listitem arg0, Object arg1, int arg2) throws Exception {
		NewServiceDTO service = (NewServiceDTO) arg1;		
		Listcell lcTipo = new Listcell(service.getTipo());
		Listcell lcFechaI = new Listcell(service.getFechaInicio());
		Listcell lcFechaF = new Listcell(service.getFechaFin());
		Listcell lcMascota = new Listcell(String.valueOf(service.getMascotaId()));
		Listcell lcPersonal = new Listcell(String.valueOf(service.getPersonalCedula()));
		
		arg0.appendChild(lcTipo);
		arg0.appendChild(lcFechaI);
		arg0.appendChild(lcFechaF);
		arg0.appendChild(lcMascota);
		arg0.appendChild(lcPersonal);
		
	}

}
