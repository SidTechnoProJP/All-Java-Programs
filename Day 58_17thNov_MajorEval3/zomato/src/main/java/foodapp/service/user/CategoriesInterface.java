package foodapp.service.user;

import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Categories;

import java.util.List;

public interface CategoriesInterface {
    List<Categories> showAllCategoriesOfRestaurant(String restaurantId, int pageSize, int pageNumber) throws SessionIdExpiredException;

}
