package foodapp.exception;

public class SessionIdExpiredException extends Exception {
    public SessionIdExpiredException(String message) {
        super(message);
    }
}
