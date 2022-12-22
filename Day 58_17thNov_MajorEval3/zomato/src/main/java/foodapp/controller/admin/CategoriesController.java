package foodapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Categories;
import foodapp.service.restaurant.CategoryInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoriesController {
    @Autowired
    private CategoryInterface categoryInterface;

    @PostMapping("/addCategory")
    public ResponseEntity<String> addCategories(@RequestBody List<Categories> categories) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoryInterface.addCategories(categories)));
    }

    @PatchMapping("/changeDeliveryCharge")
    public ResponseEntity<String> changeDeliveryCharge(@RequestParam int deliveryCharge, @RequestParam String categoryName)
            throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoryInterface.changeDeliveryCharge(deliveryCharge, categoryName)));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeCategory(@RequestParam String categoryName) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoryInterface.removeCategory(categoryName)));
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Categories>> viewCategories() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoryInterface.viewCategories()));
    }

    @GetMapping("/show")
    public ResponseEntity<Categories> viewCategory(@RequestParam String categoryName) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoryInterface.getParticularCategory(categoryName)));
    }
}
