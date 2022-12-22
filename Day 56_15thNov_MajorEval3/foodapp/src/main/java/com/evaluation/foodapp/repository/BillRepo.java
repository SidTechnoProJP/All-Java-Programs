package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.model.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer>{

}


