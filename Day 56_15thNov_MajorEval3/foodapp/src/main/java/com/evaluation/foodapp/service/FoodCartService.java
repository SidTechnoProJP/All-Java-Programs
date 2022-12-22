package com.evaluation.foodapp.service;

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.FoodCartException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.CustomerDTO;
import com.evaluation.foodapp.model.FoodCart;
import com.evaluation.foodapp.model.ItemDTO;

public interface FoodCartService {
	
	public FoodCart addItemToCart(String key, Integer customerId, Integer itemId) throws ItemException, CustomerException, LoginException;
	
	public FoodCart increaseItemQuantity(String key, Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException, LoginException;

	public FoodCart decreaseItemQuantity(String key, Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException, LoginException;
	
	public FoodCart removeItem(String key, Integer cartId, Integer itemId) throws FoodCartException, ItemException, LoginException;
	
	public FoodCart removeCart(String key, Integer cartId) throws FoodCartException, LoginException;
}
