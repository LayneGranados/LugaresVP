package com.special.domain;

import java.io.Serializable;
import java.util.Date;

public class Servicio implements Serializable {

    private static final long serialVersionUID = 7526472295622776147L;
	private String  codigoReserva;
	private Integer id;
	private String  horaInicio;
	private String  tipoServicio;
	private String  referencia;
	private String  pasajero;
	private Integer cantidadMinutos;
	private String  numeroVueloRecogida;
	private String  numeroVueloDestino;
	private String  lugarRecogida;
	private String  lugarDestino;
	private String  instrucciones;
	private String  letrero;
	private String  estado;
    private Empresa empresa;
	
	public Servicio() {
	}

	public Servicio(String codigoReserva, Integer id, String horaInicio,
			String tipoServicio, String referencia, String pasajero,
			Integer cantidadMinutos, String numeroVueloRecogida,
			String numeroVueloDestino, String lugarRecogida,
			String lugarDestino, String instrucciones, String letrero, String estado, Empresa empresa) {
		super();
		this.codigoReserva = codigoReserva;
		this.id = id;
		this.horaInicio = horaInicio;
		this.tipoServicio = tipoServicio;
		this.referencia = referencia;
		this.pasajero = pasajero;
		this.cantidadMinutos = cantidadMinutos;
		this.numeroVueloRecogida = numeroVueloRecogida;
		this.numeroVueloDestino = numeroVueloDestino;
		this.lugarRecogida = lugarRecogida;
		this.lugarDestino = lugarDestino;
		this.instrucciones = instrucciones;
		this.letrero = letrero;
		this.estado= estado;
        this.empresa = empresa;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(String tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getPasajero() {
		return pasajero;
	}

	public void setPasajero(String pasajero) {
		this.pasajero = pasajero;
	}

	public Integer getCantidadMinutos() {
		return cantidadMinutos;
	}

	public void setCantidadMinutos(Integer cantidadMinutos) {
		this.cantidadMinutos = cantidadMinutos;
	}

	public String getNumeroVueloRecogida() {
		return numeroVueloRecogida;
	}

	public void setNumeroVueloRecogida(String numeroVueloRecogida) {
		this.numeroVueloRecogida = numeroVueloRecogida;
	}

	public String getNumeroVueloDestino() {
		return numeroVueloDestino;
	}

	public void setNumeroVueloDestino(String numeroVueloDestino) {
		this.numeroVueloDestino = numeroVueloDestino;
	}

	public String getLugarRecogida() {
		return lugarRecogida;
	}

	public void setLugarRecogida(String lugarRecogida) {
		this.lugarRecogida = lugarRecogida;
	}

	public String getLugarDestino() {
		return lugarDestino;
	}

	public void setLugarDestino(String lugarDestino) {
		this.lugarDestino = lugarDestino;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public String getLetrero() {
		return letrero;
	}

	public void setLetrero(String letrero) {
		this.letrero = letrero;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
