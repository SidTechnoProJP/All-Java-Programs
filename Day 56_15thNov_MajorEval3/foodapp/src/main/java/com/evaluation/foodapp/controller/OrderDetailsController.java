package com.evaluation.foodapp.controller.restaurantcontroller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.FoodCartException;
import com.evaluation.foodapp.exception.ItemException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderDetailsException;
import com.evaluation.foodapp.model.OrderDetails;
import com.evaluation.foodapp.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add/{customerId}")
    public ResponseEntity<OrderDetails> saveOrderDetails(@RequestParam(required = false) String key, @PathVariable("customerId") Integer customerId) throws CustomerException, FoodCartException, LoginException {
        OrderDetails savedOrder = orderService.addOrder(key, customerId);
        return new ResponseEntity<OrderDetails>(savedOrder, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity<OrderDetails> removerOrderDetails(@RequestParam(required = false) String key, @PathVariable("orderId") Integer orderId)
            throws OrderDetailsException, LoginException {
        OrderDetails deletedOrderDetails = orderService.removeOrder(key, orderId);
        return new ResponseEntity<OrderDetails>(deletedOrderDetails, HttpStatus.OK);
    }

    @GetMapping("/view/{orderId}")
    public ResponseEntity<OrderDetails> viewOrderDetails(@RequestParam(required = false) String key, @PathVariable("orderId") Integer orderId)
            throws OrderDetailsException, LoginException {
        OrderDetails orderDetails = orderService.viewOrder(key, orderId);
        return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.OK);
    }

}
