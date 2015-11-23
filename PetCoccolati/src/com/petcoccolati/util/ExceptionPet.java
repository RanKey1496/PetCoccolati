package com.petcoccolati.util;

import org.apache.log4j.Logger;

public class ExceptionPet extends Exception{

	private String mensajeUsuario;
	private String mensajeTecnico;
	private Exception exceptionOriginal;
	private String idException;
	private static final Logger logger = Logger.getLogger(ExceptionPet.class);
	
	public String getMensajeUsuario() {
		return mensajeUsuario;
	}
	public void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = mensajeUsuario;
	}
	public String getMensajeTecnico() {
		return mensajeTecnico;
	}
	public void setMensajeTecnico(String mensajeTecnico) {
		this.mensajeTecnico = mensajeTecnico;
	}
	public Exception getExceptionOriginal() {
		return exceptionOriginal;
	}
	public void setExceptionOriginal(Exception exceptionOriginal) {
		this.exceptionOriginal = exceptionOriginal;
	}
	public String getIdException() {
		return idException;
	}
	public void setIdException(String idException) {
		this.idException = idException;
	}	
	public void pintarErrorLog(String mensajeTecnico2) {
		logger.error(mensajeTecnico2);
	}
}