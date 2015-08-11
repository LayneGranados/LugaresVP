package com.special.domain;

import java.io.Serializable;
import java.util.List;


public class Usuarios implements Serializable {
	
	private List<Usuario> users;
	 
    public List<Usuario> getUsers() {
        return users;
    }
 
    public void setUsers(List<Usuario> users) {
        this.users = users;
    }
}


