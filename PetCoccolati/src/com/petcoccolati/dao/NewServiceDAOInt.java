package com.petcoccolati.dao;

import com.petcoccolati.dto.DetalleDTO;
import com.petcoccolati.dto.FacturaDTO;
import com.petcoccolati.dto.NewServiceDTO;
import com.petcoccolati.dto.ServicioDTO;

import java.util.List;
import com.petcoccolati.util.ExceptionPet;


public interface NewServiceDAOInt {

	public void createDetalle(DetalleDTO detalle) throws ExceptionPet;
	public void createFactura(FacturaDTO factura) throws ExceptionPet;
	public int lastIdFactura() throws ExceptionPet;
	public List<ServicioDTO> listaServicios() throws ExceptionPet;
	public List<String> listNombreType() throws ExceptionPet;
	
}
