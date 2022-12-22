package com.evaluation.foodapp.controller.restaurantcontroller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.util.List;

import com.evaluation.foodapp.exception.*;
import com.google.common.net.MediaType;
import org.apache.http.impl.bootstrap.HttpServer;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.foodapp.model.Restaurant;
import com.evaluation.foodapp.service.RestaurantService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService resService;

    @Value("${project.restaurantImage}")
    private String path;

    @PostMapping("/add")
    public ResponseEntity<Restaurant> addRestaurant(@RequestParam(required = false) String key,
                                                    @RequestBody Restaurant res) throws RestaurantException, LoginException {
        Restaurant restaurant = resService.addRestaurant(key, res);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.ACCEPTED);
    }

    @PostMapping("/upload-images/{id}")
    public ResponseEntity<String> viewRestaurantImages(@RequestParam("image") MultipartFile image,
                                                       @PathVariable("id") Integer restaurantId) throws RestaurantException, LoginException {
        String fileName = null;
        try {
            fileName = this.resService.uploadRestaurantImage(restaurantId, path, image);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image already exists or it was not uploaded due to an error");

        }
        return ResponseEntity.status(HttpStatus.OK).body("Image(s) uploaded successfully!");
    }

    @GetMapping(value = "/show-images/{id}/{imageName}") //localhost:8080/show-images/1/1.png
    public ResponseEntity<?> downloadImage(
            @PathVariable("id") Integer restaurantId,
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.resService.getRestaurantImage(restaurantId, path, imageName);
        response.setContentType(String.valueOf(MediaType.ANY_IMAGE_TYPE));
        StreamUtils.copy(resource, response.getOutputStream());
        return ResponseEntity.ok(response);
    }


    @PutMapping("/update")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestParam(required = false) String key,
                                                       @RequestBody Restaurant res) throws RestaurantException, LoginException {
        Restaurant restaurant = resService.updateRestaurant(key, res);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Restaurant> deleteRestaurant(@RequestParam(required = false) String key, Integer restaurantId)
            throws RestaurantException, LoginException {
        Restaurant restaurant = resService.removeRestaurant(key, restaurantId);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<Restaurant> viewRestaurant(@RequestParam(required = false) String key,
                                                     @PathVariable("id") Integer restaurantId) throws RestaurantException, LoginException {
        Restaurant restaurant = resService.viewRestaurantById(key, restaurantId);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Restaurant>> viewAllRestaurant(@RequestParam(required = false) String key)
            throws RestaurantException, LoginException {
        List<Restaurant> restaurant = resService.getAllRestaurants(key);
        return new ResponseEntity<List<Restaurant>>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/findNearByRestaurantByCity/{city}")
    public ResponseEntity<List<Restaurant>> findNearByRestaurantByCityHandler(
            @RequestParam(required = false) String key, @PathVariable("city") String city)
            throws RestaurantException, AddressException, LoginException {
        List<Restaurant> restaurantList = resService.viewNearByRestaurant(key, city);
        return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
    }

    /*@GetMapping("/findNearestRestaurant/{pincode}")
    public ResponseEntity<List<Restaurant>> findNearestRestaurantHandler(
            @RequestParam(required = false) String key, @PathVariable("pincode") String pincode)
            throws RestaurantException, AddressException, LoginException, PincodeException {
        List<Restaurant> restaurantList = resService.viewNearestToMeRestaurant(key, pincode);
        return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
    }*/

    @GetMapping("/findNearByRestaurantByItemName/{item}")
    public ResponseEntity<List<Restaurant>> viewRestaurantByItemNameHandler(@RequestParam(required = false) String key,
                                                                            @PathVariable("item") String item) throws RestaurantException, ItemException, LoginException {
        List<Restaurant> restaurantList = resService.viewRestaurantByItemName(key, item);
        return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
    }

}
