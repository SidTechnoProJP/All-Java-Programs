package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.model.Restaurant;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RestaurantRepo extends JpaRepository<Restaurant, Integer> {
    @Modifying
    @Query(value = "update restaurant set file_name=concat(file_name,' ',?1) where restaurant_id = ?2", nativeQuery = true)
    void updateRestaurantImageFileName(String path, Integer restaurantId);
}
