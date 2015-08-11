package com.special.domain;

/**
 * Created by laynegranadosmogollon on 17/05/15.
 */
public class Estado {

    private Integer id;

    private String descripcion ;

    private String nombre ;

    private Boolean inicial;

    public Estado() {
    }

    public Estado(Integer id, String descripcion, String nombre, Boolean inicial) {
        this.id=id;
        this.descripcion=descripcion;
        this.nombre=nombre;
        this.inicial=inicial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getInicial() {
        return inicial;
    }

    public void setInicial(Boolean inicial) {
        this.inicial = inicial;
    }
}
