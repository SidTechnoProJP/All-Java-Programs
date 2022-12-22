package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.model.Restaurants;
import foodapp.service.user.RatingInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/ratingFavourites")
public class RatingController {

    /**
     * Rating Controller
     */

    @Autowired
    private RatingInterface ratingInterface;

    @PostMapping("/addToMyFavourites")
    public ResponseEntity<String> addToFavourites(@RequestPart String restaurantId) {
        return ResponseEntity.of(Optional.of(ratingInterface.addToFavourite(restaurantId)));
    }

    @PatchMapping("/rateRestaurant")
    public ResponseEntity<String> rateRestaurant(@RequestPart String orderId, @RequestParam int numberOfStarRated, @RequestPart String feedback) {
        return ResponseEntity.of(Optional.of(ratingInterface.rating(orderId, numberOfStarRated, feedback)));
    }

    @GetMapping("/viewMyFavourites")
    public ResponseEntity<List<Restaurants>> viewFavourites() {
        return ResponseEntity.of(Optional.of(ratingInterface.viewFavorites()));
    }

}