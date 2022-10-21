package com.example.demo.insurnance.repository;

import com.example.demo.insurnance.model.Accident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AccidentRepository extends JpaRepository<Accident,Integer> {

@Query(value = "select count(person.driver_id) from person,accident,participated where person.driver_id=participated.driver_id and accident.report_no=participated.report_no and accident.accident_date like concat(?1,'%')",nativeQuery = true)
    public Integer countName(Integer year);

@Query(value = "select count(driver_id) from participated where driver_id in (select driver_id from person where name = ?1)", nativeQuery = true)
    public Integer countNameOfPerson(String name);

@Modifying(clearAutomatically = true)
@Query(value = "update participated set amount=?1 where report_no=?2 and reg_no=?3",nativeQuery = true)
    public void updateDamageAmount(int amount,int report_no,int reg_no);
}
