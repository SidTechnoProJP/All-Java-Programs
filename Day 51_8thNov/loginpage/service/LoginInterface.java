package example.loginpage.service;

import example.loginpage.coustomexception.LoginFailedException;
import example.loginpage.coustomexception.SignOutException;
import example.loginpage.coustomexception.SignupException;
import example.loginpage.model.User;

public interface LoginInterface {

    String signIn(String userName , String password) throws LoginFailedException;

    String signUp(User user) throws SignupException;

    String signOut() throws SignOutException;

}
