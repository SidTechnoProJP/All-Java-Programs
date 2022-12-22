package zomatomodified.zomato.controlller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zomatomodified.zomato.entity.Categories;
import zomatomodified.zomato.model.Menu;
import zomatomodified.zomato.model.RestaurantCategory;
import zomatomodified.zomato.service.admin.MenuCategoryInterface;
import zomatomodified.zomato.service.admin.MenuInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryInterface menuCategoryInterface;

    @Autowired
    private MenuInterface menuInterface;

    @PostMapping("/add-category")
    public ResponseEntity<String> addMenu(@RequestBody List<String> categoryId, @RequestHeader String restaurantId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.addCategories(categoryId, restaurantId)));
    }

    @DeleteMapping("/remove-menu")
    public ResponseEntity<String> removeMenu(@RequestPart String restaurantId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.deleteMenu(restaurantId)));
    }

    @GetMapping("/view-categories-Available")
    public ResponseEntity<Page<Categories>> viewAllCategories(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.viewAllCategories(pageNumber, pageSize)));
    }

    @GetMapping("/view-category")
    public ResponseEntity<List<RestaurantCategory>> viewAllCategories(@RequestPart String restaurantId,
                                                                      @RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.viewRestaurantCategories(restaurantId,pageNumber,pageSize)));
    }

    @DeleteMapping("/remove-category")
    public ResponseEntity<String> removeCategory(@RequestPart String restaurantId, @RequestPart String categoryId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.deleteCategory(categoryId, restaurantId)));
    }

    @PostMapping("/add-menu")
    public ResponseEntity<String> addMenus(@ModelAttribute Menu menu) {
        return ResponseEntity.of(Optional.of(menuInterface.addMenu(menu)));
    }

}
