package foodapp.service.admin;

import org.springframework.web.multipart.MultipartFile;
import foodapp.customexceptions.UpdateFailedException;
import foodapp.model.Restaurants;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public interface RestaurantInterface {

    String register(String restaurantName, String restaurantAddress, LocalTime openAt, LocalTime closeAt, String closeOn, int deliveryCharge, MultipartFile photo) throws IOException;

    List<Restaurants> viewRestaurantDetails(int pageNumber, int pageSize);

    byte[] viewRestaurantPhoto(String restaurantId) throws IOException;

    String changeRestaurantPhoto(String restaurantId, MultipartFile photo) throws IOException, UpdateFailedException;

    String deleteRestaurantPhoto(String restaurantId) throws IOException;

    String deleteRestaurant(String restaurantId);

    String editRestaurant(Restaurants restaurant);

    Restaurants viewParticularRestaurantDetails(String restaurantId);

}
