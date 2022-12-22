package foodapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import foodapp.entity.Users;

import java.util.List;

public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findByEmail(String email);

    List<Users> findByEmailAndIsDeleted(String email, String isDeleted);

    List<Users> findByUserIdAndIsDeleted(String userId, String isDeleted);

    List<Users> findByEmailOrPhoneNumber(String email, long phoneNumber);

    List<Users> findByUserId(String userId);
}
