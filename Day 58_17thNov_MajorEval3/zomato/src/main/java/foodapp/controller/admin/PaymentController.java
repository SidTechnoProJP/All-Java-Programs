package foodapp.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.exception.SessionIdExpiredException;
import foodapp.exception.UpdateFailedException;
import foodapp.model.Payment;
import foodapp.service.restaurant.PaymentInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentInterface paymentInterface;

    @DeleteMapping("/removePaymentType")
    public ResponseEntity<String> removePaymentType(@RequestParam String paymentType) throws SessionIdExpiredException, UpdateFailedException {
        return ResponseEntity.of(Optional.of(paymentInterface.removePaymentType(paymentType)));
    }

    @PostMapping("/addPaymentType")
    public ResponseEntity<String> updatePaymentType(@RequestBody List<Payment> payments) throws SessionIdExpiredException, UpdateFailedException {
        return ResponseEntity.of(Optional.of(paymentInterface.updatePaymentType(payments)));
    }

}
