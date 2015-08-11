package com.special.domain;

public class Test {
	
	private String id;
	private String login;
	
	public Test(String id, String login) {
		this.id = id;
		this.login = login;
	}
	
	public Test() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", login=" + login + "]";
	}
	
	
	
	

}
