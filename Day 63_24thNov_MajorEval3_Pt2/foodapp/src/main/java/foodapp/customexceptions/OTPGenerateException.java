package foodapp.customexceptions;

public class OTPGenerateException extends Exception{
    public OTPGenerateException(String message) {
        super(message);
    }
}
