package foodapp.service.user;

import org.springframework.data.domain.Page;
import foodapp.exception.SessionIdExpiredException;
import foodapp.entity.Restaurants;
import foodapp.model.Categories;

import java.io.IOException;
import java.util.List;

public interface HomeInterface {

    Page<Restaurants> topRatedRestaurants(int pageNumber, int pageSize) throws SessionIdExpiredException;

    List<Categories> viewCategories(int pageNumber, int pageSize) throws SessionIdExpiredException;

    byte[] viewRestaurantPhoto(String restaurantId) throws SessionIdExpiredException, IOException;
}
