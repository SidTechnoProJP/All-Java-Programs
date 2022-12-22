package com.majorEvaluation.foodApp.service;
import com.majorEvaluation.foodApp.entity.User;
import com.majorEvaluation.foodApp.exception.InvalidUserCredentialException;
import com.majorEvaluation.foodApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

@Service
public class AdminService implements IAdminService {
    @Autowired
    UserRepository userRepository;
    private int otp;

    public String signUp(User user) throws InvalidUserCredentialException {
        Optional<User> optionalUser = userRepository.findById(user.getPhoneNumber());
        if (optionalUser.isEmpty()) {
            //if(user.getUserName())
            userRepository.save(user);
            return "Account is created";
        }
        throw new InvalidUserCredentialException("Provided details are already existing");
    }


    public boolean sendEmail(String message, String subject, String to) {
        Optional<User> user = userRepository.findUserByEmail(to);
        if (user.isEmpty()) {
            return false;
        }
        boolean f = false;
        //Variable for gmail
        String host = "smtp.gmail.com";

        String from = "4su18ec083@sdmit.in";
        //get the system properties
        Properties properties = System.getProperties();
        System.out.println("PROPERTIES " + properties);

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        //Step 1: to get the session object..
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("4su18ec083@sdmit.in", "kushilshetty");
            }


        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //adding subject to message
            m.setSubject(subject);


            //adding text to message
            // m.setText(message);
            m.setContent(message, "text/html");
            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

            System.out.println("Sent success...................");
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public ResponseEntity<String> sendOtp(String emailId) {
        otp = generateOtp();

        String subject = "Forget Password Request";
        String message = "Verify the OTP for reset password"
                + "<div style='border:1px solid #e2e2e2; padding:20px'>"
                + "<h1>"
                + "OTP is "
                + "<b>" + otp
                + "<n>"
                + "</h1>"
                + "</div>";

        String to = emailId;
        Optional<User> user = userRepository.findUserByEmail(emailId);
        if (user.isPresent()) {
            boolean flag = sendEmail(message, subject, to);
            if (flag) {
                return ResponseEntity.ok().body("OTP is successfully send to your emailID");
            } else {

                return ResponseEntity.ok().body("Can not send the otp");
            }

        } else {
            return ResponseEntity.ok().body("emailId is not registered..");
        }
    }

    public ResponseEntity<String> verifyOtp(int otp) {
        if (this.otp == otp) {
            return ResponseEntity.ok().body("OTP has been verified...\nReset your password");
        }
        return ResponseEntity.ok().body("Enter the correct otp");
    }


    public int generateOtp() {
        return new Random().nextInt(1000, 9999);
    }
}
