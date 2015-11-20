package com.petcoccolati.dto;

import java.util.Date;

public class NewServiceDTO {
	
	int id;
	String tipo;
	String fechaFin;
	String fechaInicio;
	int personalCedula;
	int mascotaId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public int getPersonalCedula() {
		return personalCedula;
	}
	public void setPersonalCedula(int personalCedula) {
		this.personalCedula = personalCedula;
	}
	public int getMascotaId() {
		return mascotaId;
	}
	public void setMascotaId(int mascotaId) {
		this.mascotaId = mascotaId;
	}
	
}
