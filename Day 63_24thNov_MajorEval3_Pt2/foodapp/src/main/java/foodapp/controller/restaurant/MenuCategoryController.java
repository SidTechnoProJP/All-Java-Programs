package foodapp.controller.restaurant;

import foodapp.entity.Categories;
import foodapp.model.Menu;
import foodapp.model.RestaurantCategory;
import foodapp.service.admin.MenuCategoryInterface;
import foodapp.service.admin.MenuInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryInterface menuCategoryInterface;

    @Autowired
    private MenuInterface menuInterface;

    @PostMapping("/addCategory")
    public ResponseEntity<String> addMenu(@RequestBody List<String> categoryId, @RequestHeader String restaurantId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.addCategories(categoryId, restaurantId)));
    }

    @DeleteMapping("/deleteMenu")
    public ResponseEntity<String> removeMenu(@RequestPart String restaurantId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.deleteMenu(restaurantId)));
    }

    @GetMapping("/showAllCategories")
    public ResponseEntity<Page<Categories>> viewAllCategories(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.viewAllCategories(pageNumber, pageSize)));
    }

    @GetMapping("/showCategory")
    public ResponseEntity<List<RestaurantCategory>> viewAllCategories(@RequestPart String restaurantId,
                                                                      @RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.viewRestaurantCategories(restaurantId,pageNumber,pageSize)));
    }

    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> removeCategory(@RequestPart String restaurantId, @RequestPart String categoryId) {
        return ResponseEntity.of(Optional.of(menuCategoryInterface.deleteCategory(categoryId, restaurantId)));
    }

    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenus(@ModelAttribute Menu menu) {
        return ResponseEntity.of(Optional.of(menuInterface.addMenu(menu)));
    }

}
