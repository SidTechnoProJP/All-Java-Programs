package foodapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import foodapp.exception.SessionIdExpiredException;
import foodapp.exception.UpdateFailedException;
import foodapp.model.Menu;
import foodapp.service.restaurant.MenuInterface;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuInterface menuInterface;

    @PostMapping("/add")
    public ResponseEntity<String> addMenu(@ModelAttribute Menu menu, @RequestParam MultipartFile dishPhoto) throws SessionIdExpiredException, IOException {
        return ResponseEntity.of(Optional.of(menuInterface.addMenu(menu, dishPhoto)));
    }

    @PatchMapping("/updateDetails")
    public ResponseEntity<String> updateMenuDetails(@RequestParam String itemName, @RequestParam String categoryName,
                                                    @RequestParam String description, @RequestParam MultipartFile dishPhoto,
                                                    @RequestParam int itemPrice, @RequestParam String oldCategoryName,
                                                    @RequestParam String newCategoryName) throws SessionIdExpiredException, UpdateFailedException, IOException {
        return ResponseEntity.of(Optional.of(menuInterface.updateMenuDetails(itemName, categoryName, description, dishPhoto, itemPrice, oldCategoryName, newCategoryName)));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeMenu(@RequestParam String itemName, @RequestParam String categoryName) throws UpdateFailedException, SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(menuInterface.removeMenu(itemName, categoryName)));
    }

    @DeleteMapping("/removeDishPhoto")
    public ResponseEntity<String> deleteOldPhoto(@RequestParam String itemName, @RequestParam String categoryName) throws IOException, SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(menuInterface.deleteOldPhoto(itemName, categoryName)));
    }

}
