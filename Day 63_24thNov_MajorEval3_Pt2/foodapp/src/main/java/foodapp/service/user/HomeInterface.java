package foodapp.service.user;

import foodapp.entity.Categories;
import foodapp.model.Restaurants;

import java.io.IOException;
import java.util.List;

public interface HomeInterface {

    List<Restaurants> topRatedRestaurants(int pageNumber, int pageSize);

    List<Categories> viewCategories(int pageNumber, int pageSize);

    byte[] viewRestaurantPhoto(String restaurantId) throws IOException;
}

