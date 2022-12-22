package com.evaluation.foodapp.service;

import com.evaluation.foodapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.exception.AdminException;
import com.evaluation.foodapp.model.Admin;
import com.evaluation.foodapp.repository.AdminRepo;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepo adminRepo;

	@Override
	public String createNewAdmin() throws AdminException {
		// TODO Auto-generated method stub

		String name = "Admin";
		String email = "admin@gmail.com";
		String password = "password";

		Admin existsAdmin = adminRepo.findByEmail(email);
		if (existsAdmin != null)
			throw new AdminException("Admin created => Email : " + email + ", Password : " + password);

		Admin admin = new Admin();
		admin.setName(name);
		admin.setEmail(email);
		admin.setPassword(password);
		
		adminRepo.save(admin);

		return "Admin created => Email : " + email + ", Password : " + password;
	}

}
