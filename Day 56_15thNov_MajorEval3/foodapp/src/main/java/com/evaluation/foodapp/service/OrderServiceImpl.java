package com.evaluation.foodapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.FoodCartException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderDetailsException;
import com.evaluation.foodapp.model.CurrentUserSession;
import com.evaluation.foodapp.model.Customer;
import com.evaluation.foodapp.model.FoodCart;
import com.evaluation.foodapp.model.Item;
import com.evaluation.foodapp.model.OrderDetails;
import com.evaluation.foodapp.model.Restaurant;
import com.evaluation.foodapp.repository.CurrentUserSessionRepo;
import com.evaluation.foodapp.repository.CustomerRepo;
import com.evaluation.foodapp.repository.OrderDetailsRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepo orderRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;


	@Override
	public OrderDetails addOrder(String key, Integer customerId) throws CustomerException, FoodCartException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("customer does not exist..!");
		Customer customer = opt.get();
		FoodCart foodCart = customer.getCart();
		List<Item> itemList = foodCart.getItemList();
		if (itemList.isEmpty())
			throw new FoodCartException("cart is empty..!");
		List<OrderDetails> orderDetailsList = orderRepo.findAll();
		boolean flag = true;
		OrderDetails orderDetails = null;
		for (int i = 0; i < orderDetailsList.size(); i++) {
			OrderDetails exOrderDetails = orderDetailsList.get(i);
			if (exOrderDetails.getFoodCart().getCartId() == foodCart.getCartId()
					&& exOrderDetails.getOrderStatus().equals("Pending")) {
				exOrderDetails.setFoodCart(foodCart);
				orderDetails = exOrderDetails;
				flag = false;
			}
		}

		if (flag) {
			orderDetails = new OrderDetails();
			orderDetails.setFoodCart(foodCart);
			orderDetails.setOrderDate(LocalDateTime.now());
			orderDetails.setOrderStatus("Pending");
		}
		orderRepo.save(orderDetails);
		return orderDetails;
	}

	@Override
	public OrderDetails removeOrder(String key, Integer orderId) throws OrderDetailsException, LoginException {
		
		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");
		
		Optional<OrderDetails> opt = orderRepo.findById(orderId);
		if (opt.isPresent()) {
			OrderDetails deletedOrder = opt.get();
			orderRepo.delete(deletedOrder);
			return deletedOrder;
		} else {
			throw new OrderDetailsException("order does not exist...!");
		}
	}

	@Override
	public OrderDetails viewOrder(String key, Integer orderId) throws OrderDetailsException, LoginException {
		
		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");
		
		Optional<OrderDetails> opt = orderRepo.findById(orderId);
		if (opt.isPresent()) {
			OrderDetails order = opt.get();
			return order;
		} else {
			throw new OrderDetailsException("order does not exist...!");
		}
	}

}
