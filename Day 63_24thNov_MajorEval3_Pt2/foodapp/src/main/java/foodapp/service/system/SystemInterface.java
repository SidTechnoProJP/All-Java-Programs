package foodapp.service.system;

import foodapp.customexceptions.EmailValidationException;
import foodapp.customexceptions.OTPGenerateException;
import foodapp.customexceptions.SessionIdExpiredException;
import foodapp.model.MyOrders;

import java.util.Map;

public interface SystemInterface {

    String generateId();

    int generateOTP();

    boolean checkAvailabilityOfEmail(String email);

    boolean checkForDuplicateRegistration(String email, long phoneNumber);

    String verifyEmail(int otp, String email) throws OTPGenerateException, EmailValidationException, SessionIdExpiredException;

    String sendOTP(String email) throws SessionIdExpiredException, OTPGenerateException;

    boolean verifyCard(long cardNumber, String userId);

    boolean verifyOrderId(String orderId);

    boolean verifyPaymentAvailability(String restaurantId, String paymentType);

    boolean verifyGstNumber(String gstNumber);

    boolean verifyDishId(String itemId);

    boolean paymentTypeExit(String paymentType, String restaurantId);

    String getUserId();

    boolean verifyMenuId(String menuId, String restaurantId);

    void verifyOrderItems(Map<String, Map<String, Integer>> orders);

    void removeOrder(String orderId);

    double calculateRatings(String restaurantId);

    String cancelOrder(MyOrders myOrders);
}
