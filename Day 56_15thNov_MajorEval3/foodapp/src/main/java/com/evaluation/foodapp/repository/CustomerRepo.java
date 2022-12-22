package com.evaluation.foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.evaluation.foodapp.model.Customer;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public Customer findByEmail(String email);

    public List<Customer> findByMobileNumber(String mobileNumber);

    @Query(value = "select * from customer where email=?1 and password=?2", nativeQuery = true)
    public Optional<Customer> findUserByEmailIdAndPassword(String emailId, String password);


    @Query(value = "select * from customer where email=?1",nativeQuery = true)
    public Optional<Customer> findUserByEmail(String emailId);

    @Modifying
    @Query(value = "update customer set password=?1 where email=?2",nativeQuery = true)
    public int resetPassword(String password,String email_id);
}
