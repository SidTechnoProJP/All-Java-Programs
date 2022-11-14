package example.loginpage.coustomexception;

public class LoginFailedException extends Exception {
    public LoginFailedException(String msg) {
        super(msg);
    }
}
