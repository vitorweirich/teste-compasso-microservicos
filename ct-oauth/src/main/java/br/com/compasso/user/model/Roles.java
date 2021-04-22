package br.com.compasso.user.model;

import java.io.Serializable;


public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String role;

	public Roles() {
		super();
	}

	public Roles(Long id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
