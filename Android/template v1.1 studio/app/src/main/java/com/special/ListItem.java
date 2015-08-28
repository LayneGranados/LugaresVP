package com.special;

import java.util.ArrayList;

public class ListItem {
	private String nombreActividad;
	private ArrayList<String> calificacion;

	public ListItem() {
	}

	public ListItem(String nombreActividad, ArrayList<String> calificacion) {
		this.nombreActividad = nombreActividad;
		this.calificacion = calificacion;
	}

	public String getNombreActividad() {
		return nombreActividad;
	}

	public void setNombreActividad(String nombreActividad) {
		this.nombreActividad = nombreActividad;
	}

	public ArrayList<String> getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(ArrayList<String> calificacion) {
		this.calificacion = calificacion;
	}
}