package com.evaluation.foodapp.service;

import java.util.List;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderHistoryException;
import com.evaluation.foodapp.model.OrderHistory;

public interface OrderHistoryService {

	public OrderHistory getOrderHistoryById(String key, Integer orderHisId)
			throws OrderHistoryException, LoginException;

	public List<OrderHistory> getOrderHistoryByCustomerId(String key, Integer customerId)
			throws OrderHistoryException, LoginException, CustomerException;

	public List<OrderHistory> getAllOrderHistory(String key) throws OrderHistoryException, LoginException;

}
