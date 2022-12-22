package foodapp.customexceptions;

public class TokenExpiredException extends Exception{
    public TokenExpiredException(String message) {
        super(message);
    }
}
