package foodapp.service.user;

import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Menu;

import java.util.List;

public interface MenuInterface {
    List<Menu> viewMenuOfRestaurant(String restaurantId, String categoryName, int pageSize, int pageNumber) throws SessionIdExpiredException;

    List<Menu> viewMenuOfCategory(String categoryName, int pageSize, int pageNumber) throws SessionIdExpiredException;

    Menu getDishDetails(String restaurantId, String categoryName ,String itemName);
}
