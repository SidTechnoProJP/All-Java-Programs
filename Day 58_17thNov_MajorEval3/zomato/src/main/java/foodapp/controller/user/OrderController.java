package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import foodapp.exception.InvalidCardNumberException;
import foodapp.exception.SessionIdExpiredException;
import foodapp.model.MyOrders;
import foodapp.service.user.OrderInterface;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderInterface orderInterface;

    @PostMapping("/makeOrder")
    public ResponseEntity<List<MyOrders>> addOrders(@RequestParam String restaurantId, @RequestParam Map<String, Map<String, Integer>> orders,
                                                    @RequestParam String paymentType) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(orderInterface.addOrders(restaurantId, orders, paymentType)));
    }

    @PostMapping("/makePayment")
    public ResponseEntity<String > makePayment(@RequestParam String restaurantId,@RequestParam String orderId,
                                               @RequestParam String paymentType,@RequestParam Long cardNumber) throws SessionIdExpiredException, InvalidCardNumberException{
        return ResponseEntity.of(Optional.of(orderInterface.makePayment(restaurantId,orderId,paymentType,cardNumber)));
    }
}
