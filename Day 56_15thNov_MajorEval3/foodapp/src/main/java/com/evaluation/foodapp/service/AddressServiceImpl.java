package com.evaluation.foodapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.exception.AddressException;
import com.evaluation.foodapp.exception.RestaurantException;
import com.evaluation.foodapp.model.Address;
import com.evaluation.foodapp.model.Restaurant;
import com.evaluation.foodapp.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService{

	@Override
	public List<Restaurant> getAllRestaurantsByAddressId(Integer addressId) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}


}
