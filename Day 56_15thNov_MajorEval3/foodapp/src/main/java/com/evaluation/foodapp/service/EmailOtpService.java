package com.evaluation.foodapp.service;

import com.evaluation.foodapp.request.OtpRequest;
import com.evaluation.foodapp.response.OtpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailOtpService {
    private static final String username = "projectbot85@gmail.com";
    private static final String password = "fhdwxoqbgrosocrs";

    public boolean sendEmail(String emailId, String twoFaCode)throws Exception{
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));

        message.setSubject("Authentication code from our Service");
        message.setText("Factor Authentication code is:"+twoFaCode);
        Transport.send(message);
        return true;

    }




    @Autowired
    JdbcTemplate jdbcTemplate;


    public void update2FAProperties(OtpRequest otpRequest, String twofacode) {
        jdbcTemplate.update("update customer set two_fa_code=?, two_fa_expire_time=? where customer_id=?", new Object[] {
                twofacode, (System.currentTimeMillis()/1000) + 120, otpRequest.getCustomer_id()
        });
    }


    public boolean checkCode(OtpResponse response, String empId) {
        return jdbcTemplate.queryForObject("select count(*) from customer where two_fa_code=? and customer_id=?"
                + " and two_fa_expire_time >=?", new Object[] {response.getOtpCode(), empId,
                System.currentTimeMillis()/1000}, Integer.class) >0;
    }
}
