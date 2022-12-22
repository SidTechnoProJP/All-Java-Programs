package com.evaluation.foodapp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.evaluation.foodapp.exception.*;
import com.evaluation.foodapp.model.Restaurant;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {

	public Restaurant addRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException;

	public Restaurant updateRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException;

	public Restaurant removeRestaurant(String key, Integer restaurantId) throws RestaurantException, LoginException;

	public Restaurant viewRestaurantById(String key, Integer restaurantId) throws RestaurantException, LoginException;

	public List<Restaurant> getAllRestaurants(String key) throws RestaurantException, LoginException;

	public List<Restaurant> viewNearByRestaurant(String key, String city)
			throws RestaurantException, AddressException, LoginException;

	/*public List<Restaurant> viewNearestToMeRestaurant(String key, String pincode)
			throws RestaurantException, PincodeException, LoginException;*/

	public List<Restaurant> viewRestaurantByItemName(String key, String itemName)
			throws RestaurantException, ItemException, LoginException;

	public String uploadRestaurantImage(Integer restaurantId, String path, MultipartFile file) throws IOException;

	InputStream getRestaurantImage(Integer restaurantId, String path, String fileName) throws FileNotFoundException;

	//public List<Restaurant> viewNearestToMeRestaurant(Integer restaurantId, Integer )
}
