package com.evaluation.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderHistoryException;
import com.evaluation.foodapp.model.CurrentUserSession;
import com.evaluation.foodapp.model.Customer;
import com.evaluation.foodapp.model.OrderHistory;
import com.evaluation.foodapp.repository.CurrentUserSessionRepo;
import com.evaluation.foodapp.repository.CustomerRepo;
import com.evaluation.foodapp.repository.OrderHistoryRepo;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private OrderHistoryRepo orderHistoryRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;

	@Override
	public OrderHistory getOrderHistoryById(String key, Integer orderHisId)
			throws OrderHistoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<OrderHistory> opt = orderHistoryRepo.findById(orderHisId);
		if (opt.isEmpty())
			throw new OrderHistoryException("Order history not found!");

		return opt.get();
	}

	@Override
	public List<OrderHistory> getOrderHistoryByCustomerId(String key, Integer customerId)
			throws OrderHistoryException, LoginException, CustomerException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");

		List<OrderHistory> orderHistoryList = orderHistoryRepo.findByCustomerId(customerId);
		if (orderHistoryList.isEmpty())
			throw new OrderHistoryException("Order history not found!");
		return orderHistoryList;

	}

	@Override
	public List<OrderHistory> getAllOrderHistory(String key) throws OrderHistoryException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			List<OrderHistory> orderHistoryList = orderHistoryRepo.findAll();
			if (orderHistoryList.isEmpty())
				throw new OrderHistoryException("Order history list is empty!");
			return orderHistoryList;
		} else
			throw new LoginException("Admin login required");

	}

}
