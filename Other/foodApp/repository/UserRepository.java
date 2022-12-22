package com.majorEvaluation.foodApp.repository;

import com.majorEvaluation.foodApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from user where email_id=?1 and password=?2", nativeQuery = true)
    public Optional<User> findUserByEmailIdAndPassword(String emailId, String password);


    @Query(value = "select * from user where email_id=?1",nativeQuery = true)
    public Optional<User> findUserByEmail(String emailId);

    @Modifying
    @Query(value = "update user set password=?1 where email_id=?2",nativeQuery = true)
    public int resetPassword(String password,String email_id);
}