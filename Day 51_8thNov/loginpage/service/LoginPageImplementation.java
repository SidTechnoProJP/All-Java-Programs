package example.loginpage.service;

import example.loginpage.coustomexception.LoginFailedException;
import example.loginpage.coustomexception.SignOutException;
import example.loginpage.coustomexception.SignupException;
import example.loginpage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class LoginPageImplementation implements LoginInterface {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sessionId;

    private String username;

    @Override
    public String signIn(String userName, String password) throws LoginFailedException {
        try {
            if (sessionId == null) {
                if (jdbcTemplate.query("select *  from user where userName = ? and password  = ?",
                        new BeanPropertyRowMapper<>(User.class), userName, password).isEmpty())
                    return "Invalid username/password";
                this.username = userName;
                this.sessionId = generateSessionId();
                return "Login Successful with Session Id : " + sessionId;
            }
            return "you are already logged in. please logout for new sign in";

        } catch (DataAccessException exception) {
            throw new LoginFailedException("Failed login");
        }
    }

    @Override
    public String signUp(User user) throws SignupException {
        try {
            if (sessionId == null) {
                if (jdbcTemplate.query("Select * from user where username = ? ",
                        new BeanPropertyRowMapper<>(User.class), user.getUserName()).isEmpty()) {
                    if (jdbcTemplate.query("Select * from user where email = ? ",
                            new BeanPropertyRowMapper<>(User.class), user.getEmail()).isEmpty()) {
                        jdbcTemplate.update("Insert into user values(?,?,?,?,?,?,?)", user.getUserName(), user.getName(), user.getEmail(),
                                LocalDate.now(), user.getDateOfBirth(), user.getPhoneNumber(), user.getPassword());
                        return "Account successfully created";
                    }
                    return "Email is already registered.";
                }
                return "username is already taken try with other username.";
            }
            return "you are already logged in. please logout for new sign up";
        } catch (DataAccessException exception) {
            throw new SignupException("Failed to register.Please provide valid details");
        }
    }

    @Override
    public String signOut() throws SignOutException {
        try {
            this.username = null;
            this.sessionId = null;
            return "sign out successful";
        } catch (Exception exception) {
            throw new SignOutException("sign out failed.try again after sometime.");
        }
    }

    protected String generateSessionId() {
        return Integer.toString(new Random().nextInt(1000, 10000));
    }
}
