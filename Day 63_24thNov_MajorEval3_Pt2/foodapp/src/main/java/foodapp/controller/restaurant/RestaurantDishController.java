package foodapp.controller.restaurant;

import foodapp.customexceptions.UpdateFailedException;
import foodapp.model.Dish;
import foodapp.service.admin.DishInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantDishController {

    @Autowired
    private DishInterface dishInterface;

    @PostMapping("/addDish")
    public ResponseEntity<String> addDish(@RequestBody List<Dish> dishes) {
        return ResponseEntity.of(Optional.of(dishInterface.addDish(dishes)));
    }

    @PatchMapping("/updateDishPhoto")
    public ResponseEntity<String> changeDishPhoto(@RequestPart String itemId, @RequestPart MultipartFile dishPhoto) throws UpdateFailedException, IOException {
        return ResponseEntity.of(Optional.of(dishInterface.changeDishPhoto(itemId, dishPhoto)));
    }

    @PutMapping("/updateDish")
    public ResponseEntity<String> edit(@ModelAttribute Dish dish) throws UpdateFailedException {
        return ResponseEntity.of(Optional.of(dishInterface.editDish(dish)));
    }

    @DeleteMapping("/deleteDish")
    public ResponseEntity<String> removeDish(@RequestPart String itemId) throws UpdateFailedException {
        return ResponseEntity.of(Optional.of(dishInterface.removeDish(itemId)));
    }

    @DeleteMapping("/deleteDishPhoto")
    public ResponseEntity<String> deleteOldPhoto(@RequestPart String itemId) throws IOException {
        return ResponseEntity.of(Optional.of(dishInterface.deleteDishPhoto(itemId)));
    }

    @GetMapping("/showAllDishes")
    public ResponseEntity<List<Dish>> viewAllDishOfRestaurant(@RequestHeader String restaurantId, @RequestParam int pageNumber,
                                                              @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(dishInterface.viewAllDishOfRestaurant(restaurantId, pageNumber, pageSize)));
    }
}
