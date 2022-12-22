package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.model.Item;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{
	
	public Item findByItemName(String item) throws ItemException;
	
	
}
