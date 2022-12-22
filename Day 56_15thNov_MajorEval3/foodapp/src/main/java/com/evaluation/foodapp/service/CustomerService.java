package com.evaluation.foodapp.service;

import java.util.List;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.InvalidUserCredentialException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

	Customer addCustomer(Customer customer) throws CustomerException;

	Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	Customer removeCustomerById(String key, Integer customerId) throws CustomerException, LoginException;

	Customer removeCustomer(String key, Customer customer) throws CustomerException, LoginException;

	Customer viewCustomer(String key, Integer customerId) throws CustomerException, LoginException;

	List<Customer> viewAllCustomers(String key) throws CustomerException, LoginException;

}
