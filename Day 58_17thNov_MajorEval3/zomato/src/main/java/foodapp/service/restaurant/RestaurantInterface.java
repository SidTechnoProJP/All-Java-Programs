package foodapp.service.restaurant;

import foodapp.exception.InvalidTimeException;
import org.springframework.web.multipart.MultipartFile;
import foodapp.exception.SessionIdExpiredException;
import foodapp.exception.UpdateFailedException;
import foodapp.entity.Restaurants;

import java.io.IOException;
import java.time.LocalTime;

public interface RestaurantInterface {

    String Login(String restaurantId) throws SessionIdExpiredException;

    String register(String restaurantId, String restaurantName, String restaurantAddress, LocalTime open, LocalTime close, String closedDay, MultipartFile photo) throws IOException, SessionIdExpiredException;

    Restaurants viewRestaurantDetails() throws SessionIdExpiredException;

    byte[] viewRestaurantPhoto() throws SessionIdExpiredException, IOException;
    /*String changeRestaurantPhoto() throws IOException, SessionIdExpiredException, UpdateFailedException;

    String changeOpenTime() throws SessionIdExpiredException, InvalidTimeException;

    String changeCloseTime() throws SessionIdExpiredException, InvalidTimeException;

    String changeCloseDay() throws SessionIdExpiredException, UpdateFailedException;

    String changeRestaurantAddress() throws SessionIdExpiredException, UpdateFailedException;*/

    String updateRestaurantDetails(String restaurantName, String restaurantAddress, String day, MultipartFile photo, int openHour, int openMinutes, int closeHour, int closeMinutes) throws SessionIdExpiredException, UpdateFailedException, IOException, InvalidTimeException;

    String deleteRestaurantPhoto() throws SessionIdExpiredException, IOException;

    String deleteRestaurant() throws SessionIdExpiredException;

    String logout() throws SessionIdExpiredException;
}
