package com.asith.gmdb.eao;

import java.util.List;

import com.asith.gmdb.entity.Admin;

public interface AdminEao {

	void saveAdmin(Admin admin);

	Admin getAdmin();

	List<Admin> getAdmin(String password);

}
