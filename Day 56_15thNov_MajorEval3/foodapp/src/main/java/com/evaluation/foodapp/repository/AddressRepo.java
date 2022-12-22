package com.evaluation.foodapp.repository;

import com.evaluation.foodapp.exception.LatLongException;
import com.evaluation.foodapp.exception.PincodeException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.exception.AddressException;
import com.evaluation.foodapp.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

	public Address findByCity(String city) throws AddressException;

	public Address findByPincode(String pincode) throws PincodeException;

	//public Address findNearestByLatLong(double latitude, double longitude) throws LatLongException;
	
}
