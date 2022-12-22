package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.model.Dish;
import foodapp.service.user.DishInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class DishController {

    @Autowired
    private DishInterface dishInterface;

    @GetMapping("/showRestaurantDish")
    public ResponseEntity<List<Dish>> viewDishOfRestaurant(@RequestPart String restaurantId, @RequestParam int pageSize,
                                                           @RequestParam int pageNumber) {
        return ResponseEntity.of(Optional.of(dishInterface.viewDishOfRestaurant(restaurantId, pageSize, pageNumber)));
    }

    @GetMapping("/showCategoryDish")
    public ResponseEntity<List<Dish>> viewDishOfCategory(@RequestHeader String categoryId, @RequestHeader String restaurantId,
                                                         @RequestParam int pageSize, @RequestParam int pageNumber) {
        return ResponseEntity.of(Optional.of(dishInterface.viewDishOfCategory(restaurantId, categoryId, pageSize, pageNumber)));
    }

    @GetMapping("/showDishDetails")
    public ResponseEntity<List<Dish>> getDishDetails(@RequestPart String itemId) {
        return ResponseEntity.of(Optional.of(dishInterface.getDishDetails(itemId)));
    }

}
