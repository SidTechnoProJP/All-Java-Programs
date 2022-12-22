package com.evaluation.foodapp.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.evaluation.foodapp.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.model.Address;
import com.evaluation.foodapp.model.CurrentUserSession;
import com.evaluation.foodapp.model.Item;
import com.evaluation.foodapp.model.Restaurant;
import com.evaluation.foodapp.repository.AddressRepo;
import com.evaluation.foodapp.repository.CurrentUserSessionRepo;
import com.evaluation.foodapp.repository.ItemRepo;
import com.evaluation.foodapp.repository.RestaurantRepo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo resRepo;

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Restaurant addRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Address address = restaurant.getAddress();
			address.getRestaurantList().add(restaurant);

			List<Item> itemList = restaurant.getItemList();
			for (Item ele : itemList) {
				ele.getRestaurants().add(restaurant);
			}
			return resRepo.save(restaurant);
		} else
			throw new LoginException("Admin login required");
	}

	@Override
	public Restaurant updateRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Restaurant> opt = resRepo.findById(restaurant.getRestaurantId());
			if (opt.isPresent()) {
				return resRepo.save(restaurant);
			} else {
				throw new RestaurantException("Restaurant id not found!");
			}
		} else
			throw new LoginException("Admin login required");
	}

	@Override
	public Restaurant removeRestaurant(String key, Integer restaurantId) throws RestaurantException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Restaurant> opt = resRepo.findById(restaurantId);
			if (opt.isPresent()) {
				Restaurant restaurant = opt.get();
				resRepo.delete(restaurant);
				return restaurant;
			} else {
				throw new RestaurantException("Restaurant id not found!");
			}
		} else
			throw new LoginException("Admin login required");
	}

	@Override
	public Restaurant viewRestaurantById(String key, Integer restaurantId) throws RestaurantException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Restaurant> opt = resRepo.findById(restaurantId);
		if (opt.isPresent()) {
			Restaurant restaurant = opt.get();
			return restaurant;
		} else {
			throw new RestaurantException("Restaurant id not found!");
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants(String key) throws RestaurantException, LoginException {
		// TODO Auto-generated method stub

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		List<Restaurant> restaurantList = resRepo.findAll();
		if (!restaurantList.isEmpty()) {
			return restaurantList;
		} else {
			throw new RestaurantException("Empty!");
		}
	}

	@Override
	public List<Restaurant> viewNearByRestaurant(String key, String city)
			throws RestaurantException, AddressException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Address address = addressRepo.findByCity(city);
		if (address != null) {
			List<Restaurant> restaurantList = address.getRestaurantList();
			if (!restaurantList.isEmpty()) {
				return restaurantList;
			} else {
				throw new RestaurantException("No restaurant found!");
			}
		} else {
			throw new AddressException("No address found!");
		}
	}

	/*@Override
	public List<Restaurant> viewNearestToMeRestaurant(String key, String pincode)
			throws RestaurantException, PincodeException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Address address = addressRepo.findByPincode(pincode);
		if (address != null) {
			List<Restaurant> restaurantList = address.getRestaurantList();
			if (!restaurantList.isEmpty()) {
				return restaurantList;
			} else {
				throw new RestaurantException("No nearest restaurant found!");
			}
		} else {
			throw new PincodeException("No nearest restaurant found!");
		}
	}*/

	@Override
	public List<Restaurant> viewRestaurantByItemName(String key, String itemName)
			throws RestaurantException, ItemException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Item item = itemRepo.findByItemName(itemName);
		if (item == null) {
			throw new ItemException("Item not found!");
		} else {
			List<Restaurant> restaurantList = item.getRestaurants();
			if (!restaurantList.isEmpty()) {
				return restaurantList;
			} else {
				throw new RestaurantException("Restaurant not found!");
			}
		}
	}

	@Override
	public String uploadRestaurantImage(Integer restaurantId, String path, MultipartFile file) throws IOException {
		//File name
		String name = file.getOriginalFilename();

		//Full path
		String filePath = path + File.separator+name;

		//Create folder if not created
		File f = new File(path);

		if(!f.exists()){
			f.mkdir();
		}

		//copy file to path
		Files.copy(file.getInputStream(), Paths.get(filePath));

		//resRepo.updateRestaurantImageFileName(filePath, restaurantId);
		jdbcTemplate.update("update restaurant set file_name=concat(file_name,' ',?) where restaurant_id = ?",filePath,restaurantId);

		return name;
	}

	@Override
	public InputStream getRestaurantImage(Integer restaurantId, String path, String fileName) throws FileNotFoundException {
		String fullPath = path+File.separator+fileName;
		InputStream inputStream = new FileInputStream(fullPath);

		return inputStream;
	}

}
