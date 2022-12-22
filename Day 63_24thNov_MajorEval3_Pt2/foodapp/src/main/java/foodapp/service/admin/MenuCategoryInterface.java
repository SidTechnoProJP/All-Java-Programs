package foodapp.service.admin;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestPart;
import foodapp.entity.Categories;
import foodapp.model.RestaurantCategory;

import java.util.List;

public interface MenuCategoryInterface {

    String addCategories(List<String> categoryId, String restaurantId);

    String deleteMenu(String restaurantId);

    String deleteCategory(String categoryId, String restaurantId);

    List<RestaurantCategory> viewRestaurantCategories(String restaurantId, int pageNumber, int pageSize);

    Page<Categories> viewAllCategories(int pageNumber, int pageSize);
}
