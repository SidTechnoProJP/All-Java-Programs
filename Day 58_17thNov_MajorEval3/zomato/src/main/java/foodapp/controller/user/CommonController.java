package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Categories;
import foodapp.model.Menu;
import foodapp.service.user.CategoriesInterface;
import foodapp.service.user.MenuInterface;
import foodapp.service.user.SearchInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homeFilter")
public class CommonController {

    @Autowired
    private MenuInterface menuInterface;

    @Autowired
    private CategoriesInterface categoriesInterface;

    @Autowired
    private SearchInterface searchInterface;

    @GetMapping("/showAllMenu")
    public ResponseEntity<List<Menu>> viewAllMenu(@RequestParam String categoryName, @RequestParam int pageSize,
                                                  @RequestParam int pageNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(menuInterface.viewMenuOfCategory(categoryName, pageSize, pageNumber)));
    }

    @GetMapping("/showCategories")
    public ResponseEntity<List<Categories>> showAllCategoriesOfRestaurant(@RequestParam String restaurantId, @RequestParam int pageSize,
                                                                          @RequestParam int pageNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(categoriesInterface.showAllCategoriesOfRestaurant(restaurantId, pageSize, pageNumber)));
    }

    @GetMapping("/showMenu")
    public ResponseEntity<List<Menu>> viewMenuOfRestaurant(@RequestParam String restaurantId, @RequestParam String categoryName,
                                                           @RequestParam int pageSize, @RequestParam int pageNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(menuInterface.viewMenuOfRestaurant(restaurantId, categoryName, pageSize, pageNumber)));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchField(@RequestParam String field) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(searchInterface.search(field)));
    }

}
