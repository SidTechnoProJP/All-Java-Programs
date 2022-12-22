package foodapp.exception;

public class InvalidCardNumberException extends Exception{
    public InvalidCardNumberException(String message) {
        super(message);
    }
}
