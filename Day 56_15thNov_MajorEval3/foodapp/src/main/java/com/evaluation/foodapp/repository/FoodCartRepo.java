package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.model.FoodCart;

@Repository
public interface FoodCartRepo extends JpaRepository<FoodCart, Integer>{

}
