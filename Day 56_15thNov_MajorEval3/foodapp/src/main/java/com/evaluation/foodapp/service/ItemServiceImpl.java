package com.evaluation.foodapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.evaluation.foodapp.exception.CategoryException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.Category;
import com.evaluation.foodapp.model.CategoryDTO;
import com.evaluation.foodapp.model.CurrentUserSession;
import com.evaluation.foodapp.model.Item;
import com.evaluation.foodapp.model.ItemDTO;
import com.evaluation.foodapp.repository.CategoryRepo;
import com.evaluation.foodapp.repository.CurrentUserSessionRepo;
import com.evaluation.foodapp.repository.ItemRepo;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Item addItem(String key, Item item) throws ItemException, CategoryException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Item existedItem = itemRepo.findByItemName(item.getItemName());
			if (existedItem == null) {

				Category exCategory = categoryRepo.findByCategoryName(item.getCategory().getCategoryName());
				if (exCategory != null) {
					exCategory.getItemList().add(item);
					item.setCategory(exCategory);
					return itemRepo.save(item);
				} else {
					throw new CategoryException("Category doesn't exists!");
				}
			} else {
				throw new ItemException("Item name already exits!");
			}
		} else
			throw new LoginException("Admin login required");
	}

	@Override
	public Item updateItem(String key, ItemDTO itemDTO) throws ItemException, CategoryException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Item> opt = itemRepo.findById(itemDTO.getItemId());
			if (opt.isEmpty())
				throw new ItemException("Item not found!");

			Item item = opt.get();
			if (itemDTO.getCatergoryId() != null) {
				Optional<Category> cat = categoryRepo.findById(itemDTO.getCatergoryId());
				if (cat.isEmpty())
					throw new CategoryException("Category not found!");

				Category category = item.getCategory();
				category.getItemList().remove(item);

				item.setCategory(cat.get());
				cat.get().getItemList().add(item);
			}

			if (itemDTO.getCost() != null)
				item.setCost(itemDTO.getCost());

			if (itemDTO.getQuantity() != null)
				item.setQuantity(itemDTO.getQuantity());

			if (itemDTO.getItemName() != null)
				item.setItemName(itemDTO.getItemName());

			return itemRepo.save(item);

		} else
			throw new LoginException("Admin login required");

	}

	@Override
	public Item removeItem(String key, ItemDTO itemDTO) throws ItemException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Item> opt = itemRepo.findById(itemDTO.getItemId());
			if (opt.isPresent()) {
				Item deletedItem = opt.get();
				itemRepo.delete(deletedItem);
				return deletedItem;
			} else {
				throw new ItemException("Item id not found..!");
			}

		} else
			throw new LoginException("Admin login required");

	}

	@Override
	public Item removeItemById(String key, Integer itemId) throws ItemException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess != null && currSess.getRole().equalsIgnoreCase("admin")) {

			Optional<Item> opt = itemRepo.findById(itemId);
			if (opt.isPresent()) {
				Item deletedItem = opt.get();
				itemRepo.delete(deletedItem);
				return deletedItem;
			} else {
				throw new ItemException("Item id not found..!");
			}

		} else
			throw new LoginException("Admin login required");
	}

	@Override
	public List<Item> getAllItem(String key) throws ItemException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		List<Item> itemList = itemRepo.findAll();
		if (itemList.isEmpty()) {
			throw new ItemException("Empty item list..!");
		} else {
			return itemList;
		}

	}

	@Override
	public List<Item> getAllItemByCategory(String key, CategoryDTO categoryDTO)
			throws ItemException, CategoryException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Category category = categoryRepo.findByCategoryName(categoryDTO.getCategoryName());
		if (category != null) {
			List<Item> itemList = category.getItemList();
			if (itemList.isEmpty()) {
				throw new ItemException("No item found in this category..!");
			} else {
				return itemList;
			}
		} else {
			throw new CategoryException("Category does not exist..!");
		}
	}

	@Override
	public List<Item> getAllItemByCategoryName(String key, String categoryName)
			throws ItemException, CategoryException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Category category = categoryRepo.findByCategoryName(categoryName);
		if (category != null) {
			List<Item> itemList = category.getItemList();
			if (itemList.isEmpty()) {
				throw new ItemException("No item found in this category..!");
			} else {
				return itemList;
			}
		} else {
			throw new CategoryException("Category does not exist..!");
		}
	}

	@Override
	public String uploadItemImage(Integer itemId, String path, MultipartFile file) throws IOException {
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
		jdbcTemplate.update("update item set file_name=concat(file_name,' ',?) where item_id = ?",filePath,itemId);

		return name;
	}
}
