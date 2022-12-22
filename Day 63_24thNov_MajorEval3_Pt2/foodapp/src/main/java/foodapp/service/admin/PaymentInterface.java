package foodapp.service.admin;

import foodapp.customexceptions.UpdateFailedException;
import foodapp.model.Payment;

import java.util.List;

public interface PaymentInterface {

    String updatePaymentType(List<Payment> payments,String restaurantId) throws UpdateFailedException;

    String removePaymentType(String paymentType,String restaurantId) throws UpdateFailedException;

}
