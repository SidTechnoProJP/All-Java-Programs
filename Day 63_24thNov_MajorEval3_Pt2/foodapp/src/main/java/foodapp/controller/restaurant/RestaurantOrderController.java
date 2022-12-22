package foodapp.controller.restaurant;

import foodapp.model.RestaurantOrders;
import foodapp.service.admin.RestaurantOrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class RestaurantOrderController {

    @Autowired
    private RestaurantOrderInterface restaurantOrderInterface;

    @GetMapping("/showOrders")
    public ResponseEntity<List<RestaurantOrders>> getOrder(@RequestPart String orderId) {
        return ResponseEntity.of(Optional.of(restaurantOrderInterface.viewCurrentDayOrders(orderId)));
    }

    @GetMapping("/showAllOrders")
    public ResponseEntity<List<RestaurantOrders>> getAllOrder(@RequestPart String restaurantId) {
        return ResponseEntity.of(Optional.of(restaurantOrderInterface.viewAllOrders(restaurantId)));
    }

    @GetMapping("/showOrdersByDate")
    public ResponseEntity<List<RestaurantOrders>> getByDate(@RequestPart String restaurantId, @RequestParam @DateTimeFormat
            (iso = DateTimeFormat.ISO.DATE) LocalDate ordersDate) {
        return ResponseEntity.of(Optional.of(restaurantOrderInterface.viewOrdersByDate(ordersDate, restaurantId)));
    }

}
