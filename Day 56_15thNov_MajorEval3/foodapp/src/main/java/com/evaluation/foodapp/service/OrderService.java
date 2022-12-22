package com.evaluation.foodapp.service;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.FoodCartException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderDetailsException;
import com.evaluation.foodapp.model.OrderDetails;

public interface OrderService {
	//method to place an order
	public OrderDetails addOrder(String key, Integer customerId)
			throws CustomerException, FoodCartException, LoginException;

	public OrderDetails removeOrder(String key, Integer orderId) throws OrderDetailsException, LoginException;

	public OrderDetails viewOrder(String key, Integer orderId) throws OrderDetailsException, LoginException;

}
