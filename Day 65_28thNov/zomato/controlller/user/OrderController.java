package zomatomodified.zomato.controlller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zomatomodified.zomato.coustomexcptions.InvalidCardNumberException;
import zomatomodified.zomato.model.MyOrderItems;
import zomatomodified.zomato.model.MyOrders;
import zomatomodified.zomato.service.user.OrderInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user/order")
public class OrderController {


    /**
     * ******Order Controller******
     */

    @Autowired
    private OrderInterface orderInterface;

    @PostMapping("/place-order")
    public ResponseEntity<MyOrders> addOrders(@RequestHeader String restaurantId, @RequestBody Map<String, Map<String, Integer>> orders,
                                                    @RequestHeader String paymentType, @RequestHeader String deliveryAddress) {
        return ResponseEntity.of(Optional.of(orderInterface.addOrders(restaurantId, orders, paymentType, deliveryAddress).get(0)));
    }

    @PostMapping("/make-payment")
    public ResponseEntity<String> makePayment(@RequestPart String restaurantId, @RequestPart String orderId,
                                              @RequestHeader String paymentType, @RequestHeader(required = false) Long cardNumber,
                                              @RequestPart String deliveryAddress) throws InvalidCardNumberException {
        return ResponseEntity.of(Optional.of(orderInterface.makePayment(restaurantId, orderId, paymentType, cardNumber, deliveryAddress)));
    }

    @GetMapping("/my-orders")
    public ResponseEntity<List<MyOrders>> myOrders(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(orderInterface.viewOrders(pageNumber, pageSize)));
    }

    @GetMapping("/my-order-detail")
    public ResponseEntity<MyOrders> myOrder(@RequestParam String orderId) {
        return ResponseEntity.of(Optional.of(orderInterface.getOrderDetails(orderId)));
    }


    @GetMapping("/my-ordered-items")
    public ResponseEntity<List<MyOrderItems>> myOrderedItem(@RequestParam String orderId, @RequestParam int pageNumber,
                                                            @RequestParam int pageSize) {
        return ResponseEntity.of(Optional.of(orderInterface.viewItemsOfOrder(orderId, pageNumber, pageSize)));
    }

    @PatchMapping("/change-delivery-address")
    public ResponseEntity<String> changeDeliveryAddress(@RequestHeader String deliveryAddress, @RequestPart String orderId) {
        return ResponseEntity.of(Optional.of(orderInterface.changeDeliverAddress(deliveryAddress, orderId)));
    }

    @DeleteMapping("/cancel-order")
    public ResponseEntity<String> cancelOrder(@RequestPart String orderId) {
        return ResponseEntity.of(Optional.of(orderInterface.cancelOrder(orderId)));
    }

    @DeleteMapping("/cancel-item")
    public ResponseEntity<String> cancelItem(@RequestPart String orderId) {
        return ResponseEntity.of(Optional.of(orderInterface.cancelItem(orderId)));
    }

}
