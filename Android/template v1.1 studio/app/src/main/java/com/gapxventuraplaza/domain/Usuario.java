package com.gapxventuraplaza.domain;

import java.io.Serializable;
import java.util.List;


public class Usuario implements Serializable{
    private String name;
    private String email;
    private String pass;
    private List<Usuarios> friends;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public List<Usuarios> getFriends() {
        return friends;
    }
 
    public void setFriends(List<Usuarios> friends) {
        this.friends = friends;
    }
 
    public String getPass() {
        return pass;
    }
 
    public void setPass(String pass) {
        this.pass = pass;
    }

}

