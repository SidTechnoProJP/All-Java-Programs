package com.evaluation.foodapp.service;

import java.io.IOException;
import java.util.List;

import com.evaluation.foodapp.exception.CategoryException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.CategoryDTO;
import com.evaluation.foodapp.model.Item;
import com.evaluation.foodapp.model.ItemDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

	public Item addItem(String key, Item item) throws ItemException, CategoryException, LoginException;

	public Item updateItem(String key, ItemDTO itemDTO) throws ItemException, CategoryException, LoginException;

	public Item removeItem(String key, ItemDTO itemDTO) throws ItemException, LoginException;

	public Item removeItemById(String key, Integer itemId) throws ItemException, LoginException;

	public List<Item> getAllItem(String key) throws ItemException, LoginException;

	public List<Item> getAllItemByCategory(String key, CategoryDTO categoryDTO)
			throws ItemException, CategoryException, LoginException;

	public List<Item> getAllItemByCategoryName(String key, String categoryName)
			throws ItemException, CategoryException, LoginException;

	public String uploadItemImage(Integer itemId, String path, MultipartFile file) throws IOException;

}
