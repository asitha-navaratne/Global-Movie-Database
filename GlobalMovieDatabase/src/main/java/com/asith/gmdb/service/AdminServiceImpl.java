package com.asith.gmdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asith.gmdb.eao.AdminEao;
import com.asith.gmdb.entity.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminEao adminEao;

	@Override
	@Transactional
	public void createAdmin() {
		Admin admin = adminEao.getAdmin();
		if(admin == null) {
			admin = new Admin();
			admin.setAdminId(101);
			admin.setAdminPassword("admin123");
			
			adminEao.saveAdmin(admin);
		}
	}

	@Override
	@Transactional
	public Admin verifyAdmin(Admin admin) {
		List<Admin> adminList = adminEao.getAdmin(admin.getAdminPassword());
		
		if(adminList.isEmpty()) {
			return null;
		}
		else {
			return adminList.get(0);
		}
	}

	@Override
	@Transactional
	public void updateAdmin(Admin admin) {
		adminEao.saveAdmin(admin);
	}

	@Override
	public boolean verifyPasswords(String password1, String password2) {
		return password1.equals(password2);
	}
}
