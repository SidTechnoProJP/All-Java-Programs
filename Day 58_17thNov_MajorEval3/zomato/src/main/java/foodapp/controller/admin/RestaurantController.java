package foodapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import foodapp.exception.InvalidTimeException;
import foodapp.exception.SessionIdExpiredException;
import foodapp.exception.UpdateFailedException;
import foodapp.entity.Restaurants;
import foodapp.service.restaurant.RestaurantInterface;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantInterface restaurantInterface;

    @GetMapping("/showAll")
    public ResponseEntity<Restaurants> viewDetails() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(restaurantInterface.viewRestaurantDetails()));
    }

    @GetMapping("/showPhoto")
    public ResponseEntity<?> viewPhoto() throws SessionIdExpiredException, IOException {
        byte[] image = restaurantInterface.viewRestaurantPhoto();
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PatchMapping("/updateDetails")
    public ResponseEntity<String> updateRestaurantDetails(@RequestParam String restaurantName, @RequestParam String restaurantAddress,
                                                          @RequestParam String closeDay, @RequestParam MultipartFile restaurantPhoto,
                                                          @RequestParam int openHour, @RequestParam int openMinutes,
                                                          @RequestParam int closeHour, @RequestParam int closeMinutes)
            throws SessionIdExpiredException, UpdateFailedException, InvalidTimeException, IOException {
        return ResponseEntity.of(Optional.of(restaurantInterface.updateRestaurantDetails(restaurantName, restaurantAddress, closeDay, restaurantPhoto, openHour, openMinutes, closeHour, closeMinutes)));
    }

    @DeleteMapping("/removePhoto")
    public ResponseEntity<String> deleteRestaurantPhoto() throws SessionIdExpiredException, IOException {
        return ResponseEntity.of(Optional.of(restaurantInterface.deleteRestaurantPhoto()));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> deleteRestaurant() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(restaurantInterface.deleteRestaurant()));
    }

}
