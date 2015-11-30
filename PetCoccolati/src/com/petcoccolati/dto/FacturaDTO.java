package com.petcoccolati.dto;

import java.util.Date;

public class FacturaDTO {

	private int id;
	private String fecha;
	private int mascota_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getMascota_id() {
		return mascota_id;
	}
	public void setMascota_id(int mascota_id) {
		this.mascota_id = mascota_id;
	}
	
}
