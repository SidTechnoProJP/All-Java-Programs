package foodapp.service.user;

import foodapp.entity.Categories;

import java.util.List;

public interface CategoriesInterface {

    List<Categories> showAllCategoriesOfRestaurant(String restaurantId, int pageSize, int pageNumber);

}
