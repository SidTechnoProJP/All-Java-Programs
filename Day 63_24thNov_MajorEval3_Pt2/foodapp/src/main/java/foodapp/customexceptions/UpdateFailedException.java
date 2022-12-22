package foodapp.customexceptions;

public class UpdateFailedException extends Exception{
    public UpdateFailedException(String message) {
        super(message);
    }
}
