package foodapp.service.restaurant;

import foodapp.exception.SessionIdExpiredException;
import foodapp.exception.UpdateFailedException;
import foodapp.model.Payment;

import java.util.List;

public interface PaymentInterface {

    String updatePaymentType(List<Payment> payments) throws SessionIdExpiredException, UpdateFailedException;

    String removePaymentType(String paymentType) throws SessionIdExpiredException, UpdateFailedException;
}
