package com.gapxventuraplaza.domain;

import java.io.Serializable;
import java.util.List;


public class Usuario implements Serializable{

    private Integer id;
    private String login;
    private String password;
    private String roldb;
    private Integer activo;

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoldb() {
        return roldb;
    }

    public void setRoldb(String roldb) {
        this.roldb = roldb;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }
}

