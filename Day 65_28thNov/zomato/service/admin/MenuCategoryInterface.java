package zomatomodified.zomato.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestPart;
import zomatomodified.zomato.entity.Categories;
import zomatomodified.zomato.model.RestaurantCategory;

import java.util.List;

public interface MenuCategoryInterface {

    String addCategories(List<String> categoryId, String restaurantId);

    String deleteMenu(String restaurantId);

    String deleteCategory(String categoryId, String restaurantId);

    List<RestaurantCategory> viewRestaurantCategories(String restaurantId, int pageNumber, int pageSize);

    Page<Categories> viewAllCategories(int pageNumber, int pageSize);
}
