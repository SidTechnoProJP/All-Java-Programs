package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Favourite;
import foodapp.service.user.RatingInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingInterface ratingInterface;

    @PostMapping("/addFavourites")
    public ResponseEntity<String> addToFavourites(@RequestParam String restaurantName) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(ratingInterface.addToFavourite(restaurantName)));
    }

    @PatchMapping("/rateRestaurant")
    public ResponseEntity<String> rateRestaurant(@RequestParam String orderId) throws SessionIdExpiredException {
        ratingInterface.rating(orderId);
        return ResponseEntity.ok("thank you for rating");
    }

    @GetMapping("/showFavourites")
    public ResponseEntity<List<Favourite>> viewFavourites() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(ratingInterface.viewFavorites()));
    }

    @DeleteMapping("/removeFavourites")
    public ResponseEntity<String> removeFavourites(@RequestParam String restaurantName) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(ratingInterface.deleteFavourite(restaurantName)));
    }
}
