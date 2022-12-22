package com.evaluation.foodapp.controller.restaurantcontroller;

import java.io.IOException;
import java.util.List;

import com.evaluation.foodapp.exception.RestaurantException;
import com.evaluation.foodapp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.foodapp.exception.CategoryException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.model.CategoryDTO;
import com.evaluation.foodapp.model.Item;
import com.evaluation.foodapp.model.ItemDTO;
import com.evaluation.foodapp.service.ItemService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Value("${project.itemImages}")
    private String path;

    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItem(@RequestParam(required = false) String key)
            throws ItemException, LoginException {
        List<Item> itemList = itemService.getAllItem(key);
        return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Item>> getAllItemByCategory(@RequestParam(required = false) String key,
                                                           @RequestBody CategoryDTO categoryDTO) throws ItemException, CategoryException, LoginException {
        List<Item> itemList = itemService.getAllItemByCategory(key, categoryDTO);
        return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
    }

    @GetMapping("/get/{categoryName}")
    public ResponseEntity<List<Item>> getAllItemByCategoryName(@RequestParam(required = false) String key,
                                                               @PathVariable String categoryName) throws ItemException, CategoryException, LoginException {
        List<Item> itemList = itemService.getAllItemByCategoryName(key, categoryName);
        return new ResponseEntity<List<Item>>(itemList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestParam(required = false) String key, @RequestBody Item item)
            throws ItemException, CategoryException, LoginException {
        Item savedItem = itemService.addItem(key, item);

        return new ResponseEntity<Item>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Item> updateItem(@RequestParam(required = false) String key, @RequestBody ItemDTO itemDTO)
            throws ItemException, CategoryException, LoginException {
        Item updatedItem = itemService.updateItem(key, itemDTO);

        return new ResponseEntity<Item>(updatedItem, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Item> removeItem(@RequestParam(required = false) String key, @RequestBody ItemDTO itemDTO)
            throws ItemException, LoginException {
        Item deletedItem = itemService.removeItem(key, itemDTO);

        return new ResponseEntity<Item>(deletedItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Item> removeItemById(@RequestParam(required = false) String key,
                                               @PathVariable("id") Integer id) throws ItemException, LoginException {
        Item deletedItem = itemService.removeItemById(key, id);

        return new ResponseEntity<Item>(deletedItem, HttpStatus.OK);
    }

    @PostMapping("/upload-item-images/{id}")
    public ResponseEntity<String> viewItemImages(@RequestParam("image") MultipartFile image,
                                                       @PathVariable("id") Integer itemId) throws ItemException, LoginException {
        String fileName = null;
        try {
            fileName = this.itemService.uploadItemImage(itemId, path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image already exists or it was not uploaded due to an error");

        }
        return ResponseEntity.status(HttpStatus.OK).body("Image(s) uploaded successfully!");
    }


}
