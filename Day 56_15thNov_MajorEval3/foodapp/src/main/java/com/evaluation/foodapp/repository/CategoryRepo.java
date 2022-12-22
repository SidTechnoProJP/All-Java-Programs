package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.exception.CategoryException;
import com.evaluation.foodapp.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

	public Category findByCategoryName(String categoryName) throws CategoryException;

	@Query("from Category where categoryName=?1")
	public Category getCategoryByName(String categoryName);

}
