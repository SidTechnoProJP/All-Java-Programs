package foodapp.controller.restaurant;

import foodapp.customexceptions.UpdateFailedException;
import foodapp.model.Restaurants;
import foodapp.service.admin.RestaurantInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantInterface restaurantInterface;

    @PostMapping("/register")
    public ResponseEntity<String> newRegistration(@RequestParam String restaurantName, @RequestParam String restaurantAddress, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime openAt,
                                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime closeAt, @RequestParam @Valid String closeOn,
                                                  @RequestParam int deliveryCharge, @RequestParam MultipartFile restaurantPhoto) throws IOException {
        return ResponseEntity.of(Optional.of(restaurantInterface.register(restaurantName, restaurantAddress, openAt, closeAt, closeOn, deliveryCharge, restaurantPhoto)));
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<Restaurants>> viewAll(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(restaurantInterface.viewRestaurantDetails(pageNumber, pageSize)));
    }

    @GetMapping("/showPhoto")
    public ResponseEntity<?> viewPhoto(@RequestPart String restaurantId) throws IOException {
        byte[] image = restaurantInterface.viewRestaurantPhoto(restaurantId);
        return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(image);
    }

    @PatchMapping("/updateRestaurantPhoto")
    public ResponseEntity<String> changeRestaurantPhoto(@RequestPart String restaurantId, @RequestPart MultipartFile restaurantPhoto)
            throws IOException, UpdateFailedException {
        return ResponseEntity.of(Optional.of(restaurantInterface.changeRestaurantPhoto(restaurantId, restaurantPhoto)));
    }

    @DeleteMapping("/deleteRestaurantPhoto")
    public ResponseEntity<String> deleteRestaurantPhoto(@RequestPart String restaurantId) throws IOException {
        return ResponseEntity.of(Optional.of(restaurantInterface.deleteRestaurantPhoto(restaurantId)));
    }

    @DeleteMapping("/deleteRestaurant")
    public ResponseEntity<String> deleteRestaurant(@RequestPart String restaurantId) {
        return ResponseEntity.of(Optional.of(restaurantInterface.deleteRestaurant(restaurantId)));
    }

    @PutMapping("/updateDetails")
    public ResponseEntity<String> edit(@RequestBody Restaurants restaurant) {
        return ResponseEntity.of(Optional.of(restaurantInterface.editRestaurant(restaurant)));
    }

    @GetMapping("/showDetail")
    public ResponseEntity<Restaurants> viewRestaurant(@RequestPart String restaurantId) throws IOException {
        return ResponseEntity.of(Optional.of(restaurantInterface.viewParticularRestaurantDetails(restaurantId)));
    }
}

