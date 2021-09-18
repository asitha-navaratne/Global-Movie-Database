package com.asith.gmdb.service;

import com.asith.gmdb.entity.Admin;

public interface AdminService {

	void createAdmin();

	Admin verifyAdmin(Admin admin);

	void updateAdmin(Admin admin);

	boolean verifyPasswords(String password1, String password2);

}
