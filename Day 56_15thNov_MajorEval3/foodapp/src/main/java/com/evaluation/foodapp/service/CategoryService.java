package com.evaluation.foodapp.service;

import java.util.List;

import com.evaluation.foodapp.exception.CategoryException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.Category;

public interface CategoryService {

	public Category addCategory(String key, String categoryName) throws CategoryException, LoginException;

	public Category removeCategory(String key, String categoryName) throws CategoryException, LoginException;

	public List<Category> viewAllCategory(String key) throws CategoryException, LoginException;

	public Category viewCategoryById(String key, Integer categoryId) throws CategoryException, LoginException;

	public Category updateCategory(String key, Category category) throws CategoryException, LoginException;

}
