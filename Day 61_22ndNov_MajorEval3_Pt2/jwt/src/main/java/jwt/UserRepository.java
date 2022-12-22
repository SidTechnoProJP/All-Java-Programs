package jwt;

import jwt.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserByEmail(@Param("email") String email);
	@Modifying
	@Query(value = "UPDATE User u SET enabled=0 WHERE u.user_id = :userId",nativeQuery = true)
	public int blockUser(@Param("userId") Long userId);

	@Modifying
	@Query(value = "UPDATE User u SET enabled=1 WHERE u.user_id = :userId",nativeQuery = true)
	public int unBlockUser(@Param("userId") Long userId);
	@Modifying
	@Query(value = "UPDATE User u SET u.phone_number= :phone_number WHERE u.user_id = :userId",nativeQuery = true)
	public int updatePhoneNumber(@Param("phone_number") String phoneNumber,@Param("userId") Long userId);

	@Modifying
	@Query(value = "UPDATE User u SET u.address= :address WHERE u.user_id = :userId",nativeQuery = true)
	public int updateAddress(@Param("address") String address,@Param("userId") Long userId);

}
