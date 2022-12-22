package foodapp.customexceptions;

public class SessionIdExpiredException extends Exception {
    public SessionIdExpiredException(String message) {
        super(message);
    }
}
