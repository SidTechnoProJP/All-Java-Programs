package com.evaluation.foodapp.service;

import com.evaluation.foodapp.exception.BillException;
import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderDetailsException;
import com.evaluation.foodapp.model.Bill;

public interface BillService {

	public Bill generateBill(String key, Integer customerId, Integer orderDetailId)
			throws BillException, CustomerException, OrderDetailsException, LoginException;

	public Bill removeBill(String key, Integer billId) throws BillException, LoginException;

	public Bill viewBill(String key, Integer billId) throws BillException, LoginException;

}
