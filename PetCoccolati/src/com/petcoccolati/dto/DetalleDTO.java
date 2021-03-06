package com.petcoccolati.dto;

import java.util.Date;

public class DetalleDTO {

	private int facturaid;
	private int id;
	private int servicioid;
	private int personalcedula;
	private String descripcion;
	private String fechainicio;
	private String fechafin;
	private double precio;
	
	public int getFacturaid() {
		return facturaid;
	}
	public void setFacturaid(int facturaid) {
		this.facturaid = facturaid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getServicioid() {
		return servicioid;
	}
	public void setServicioid(int servicioid) {
		this.servicioid = servicioid;
	}
	public int getPersonalcedula() {
		return personalcedula;
	}
	public void setPersonalcedula(int personalcedula) {
		this.personalcedula = personalcedula;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechainicio() {
		return fechainicio;
	}
	public void setFechainicio(String fechainicio) {
		this.fechainicio = fechainicio;
	}
	public String getFechafin() {
		return fechafin;
	}
	public void setFechafin(String fechafin) {
		this.fechafin = fechafin;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
