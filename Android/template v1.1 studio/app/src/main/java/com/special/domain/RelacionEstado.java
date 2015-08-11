package com.special.domain;

/**
 * Created by laynegranadosmogollon on 17/05/15.
 */
public class RelacionEstado {

    private Integer id;

    private Integer estadoPadre;

    private Integer estadoHijo;

    private Boolean activo;

    public RelacionEstado() {
    }

    public RelacionEstado(Integer id, Integer estadoPadre, Integer estadoHijo, Boolean activo) {
        this.id = id;
        this.estadoPadre = estadoPadre;
        this.estadoHijo = estadoHijo;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstadoPadre() {
        return estadoPadre;
    }

    public void setEstadoPadre(Integer estadoPadre) {
        this.estadoPadre = estadoPadre;
    }

    public Integer getEstadoHijo() {
        return estadoHijo;
    }

    public void setEstadoHijo(Integer estadoHijo) {
        this.estadoHijo = estadoHijo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
