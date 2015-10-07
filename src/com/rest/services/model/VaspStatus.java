package com.rest.services.model;

/**
 * Bean usado para parsear la respuesta del oneapi
 * Ejemplo:
 * {
 * 		"status": 0,
 * }
 * 
 * @author Spanky
 */
public class VaspStatus {
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
