package com.evaluation.foodapp.service;

import java.util.List;

import com.evaluation.foodapp.exception.AddressException;
import com.evaluation.foodapp.model.Restaurant;

public interface AddressService {
	
	public List<Restaurant> getAllRestaurantsByAddressId(Integer addressId) throws AddressException;

}
