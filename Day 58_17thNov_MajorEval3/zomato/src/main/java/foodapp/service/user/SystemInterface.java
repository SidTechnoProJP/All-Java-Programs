package foodapp.service.user;

import foodapp.exception.EmailValidationException;
import foodapp.exception.OTPGenerateException;
import foodapp.exception.SessionIdExpiredException;

public interface SystemInterface {

    String generateId();

    int generateOTP();

    String verifyEmail(int otp, String email) throws OTPGenerateException, EmailValidationException, SessionIdExpiredException;

    String sendOTP(String email) throws SessionIdExpiredException, OTPGenerateException;

    boolean verifyCard(long cardNumber);

    boolean verifyOrderId(String orderId);

    boolean verifyPaymentAvailability(String restaurantId, String paymentType) throws SessionIdExpiredException;

    void checkSession() throws SessionIdExpiredException;
}
