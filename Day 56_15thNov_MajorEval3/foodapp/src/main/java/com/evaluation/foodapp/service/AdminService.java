package com.evaluation.foodapp.service;

import com.evaluation.foodapp.exception.AdminException;
import com.evaluation.foodapp.model.Admin;
import com.evaluation.foodapp.model.Customer;
import org.springframework.http.ResponseEntity;

public interface AdminService {

	public String createNewAdmin() throws AdminException;

}
