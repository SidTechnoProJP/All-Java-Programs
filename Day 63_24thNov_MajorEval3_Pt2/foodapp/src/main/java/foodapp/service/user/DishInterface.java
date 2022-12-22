package foodapp.service.user;

import foodapp.model.Dish;

import java.util.List;

public interface DishInterface {

    List<Dish> viewDishOfRestaurant(String restaurantId, int pageSize, int pageNumber);

    List<Dish> viewDishOfCategory(String restaurantId, String categoryId, int pageSize, int pageNumber);

    List<Dish> getDishDetails(String itemId);

}
