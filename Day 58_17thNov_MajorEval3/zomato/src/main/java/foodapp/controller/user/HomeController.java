package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import foodapp.exception.SessionIdExpiredException;
import foodapp.entity.Restaurants;
import foodapp.model.Categories;
import foodapp.service.user.HomeInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeInterface homeInterface;

    @GetMapping("/showRestaurant")
    public ResponseEntity<Page<Restaurants>> viewRestaurants(@RequestParam int pageNumber, @RequestParam int pageSize) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(homeInterface.topRatedRestaurants(pageNumber, pageSize)));
    }

    @GetMapping("/showCategories")
    public ResponseEntity<List<Categories>> viewCategories(int pageNumber, int pageSize) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(homeInterface.viewCategories(pageNumber, pageSize)));
    }

    @GetMapping("/showRestaurantPhoto")
    public ResponseEntity<?> viewRestaurantPhoto(@RequestParam String restaurantId) throws SessionIdExpiredException, IOException {
        byte[] image = homeInterface.viewRestaurantPhoto(restaurantId);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(image);
    }

}
