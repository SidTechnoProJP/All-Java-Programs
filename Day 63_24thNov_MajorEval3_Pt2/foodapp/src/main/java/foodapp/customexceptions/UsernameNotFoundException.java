package foodapp.customexceptions;

public class UsernameNotFoundException extends Exception{
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
