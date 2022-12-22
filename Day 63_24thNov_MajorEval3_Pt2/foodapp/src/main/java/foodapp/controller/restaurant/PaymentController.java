package foodapp.controller.restaurant;

import foodapp.customexceptions.UpdateFailedException;
import foodapp.model.Payment;
import foodapp.service.admin.PaymentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurant")
public class PaymentController {

    @Autowired
    private PaymentInterface paymentInterface;

    @PostMapping("/addPayment")
    public ResponseEntity<String> addPaymentTypes(@RequestBody List<Payment> payments, @RequestHeader String restaurantId) throws UpdateFailedException {
        return ResponseEntity.of(Optional.of(paymentInterface.updatePaymentType(payments, restaurantId)));
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity<String> removePaymentType(@RequestPart String paymentType, @RequestPart String restaurantId) throws UpdateFailedException {
        return ResponseEntity.of(Optional.of(paymentInterface.removePaymentType(paymentType, restaurantId)));
    }

}
