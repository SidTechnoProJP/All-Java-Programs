package foodapp.customexceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}
