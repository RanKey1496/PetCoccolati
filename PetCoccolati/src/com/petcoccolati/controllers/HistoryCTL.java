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

import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.PetDTO;
import com.petcoccolati.ngc.HistoryNGC;
import com.petcoccolati.ngc.PetNGC;
import com.petcoccolati.util.ExceptionPet;

public class HistoryCTL extends GenericForwardComposer implements ListitemRenderer{

	private Listbox lbHistory;
	private static final Logger logger = Logger.getLogger(MyPetsCTL.class);
	private HistoryNGC historyNGC;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		logger.info("Se creó MyPetsCTL");
	}

	public HistoryCTL() {
	}
	
	public void setHistoryNGC(HistoryNGC historyNGC){
		this.historyNGC = historyNGC;
	}

	public void onCreate() {
		definirModelo();
	}

	private void definirModelo() {
		List<NewServiceDTO> historyList = null;
		try {
			historyList = historyNGC.listaPets();
		} catch (ExceptionPet e) {
			e.printStackTrace();
		}
		ListModel model = new ListModelList(historyList);
		lbHistory.setModel(model);
		lbHistory.setItemRenderer(this);
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
