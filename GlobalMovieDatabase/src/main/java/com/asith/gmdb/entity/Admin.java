package com.asith.gmdb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "GMDB_ADMIN")
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	private int adminId;
	private String adminPassword;

	public Admin() {
	}

	@Id
	@Column(name = "admin_id")
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	@Column(name = "admin_password")
	@NotBlank(message = "Required")
	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
}
