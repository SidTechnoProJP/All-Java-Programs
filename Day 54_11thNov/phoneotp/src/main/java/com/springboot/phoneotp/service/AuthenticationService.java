package com.springboot.phoneotp.service;
import com.springboot.phoneotp.model.RegistrationModel;
import com.springboot.phoneotp.model.SignInModel;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springboot.phoneotp.config.TwilioConfig;
import com.springboot.phoneotp.model.OtpStatus;
import com.springboot.phoneotp.model.PasswordResetRequestDto;
import com.springboot.phoneotp.model.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

@Repository
public class AuthenticationService implements IauthenticationService {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private TwilioConfig twilioConfig;

    String otp ;
    /**
     * register the user in the database
     * @param registrationModel to store in the database
     * @return response message
     * @throws Exception
     */
    @Override
    public String register(RegistrationModel registrationModel) throws Exception {
        try {
            jdbcTemplate.update("insert into register values(?,?,?,?)",
                    registrationModel.getPhoneNumber(),registrationModel.getPassword(),registrationModel.getSecretQuestion().toLowerCase(),registrationModel.getSecretAnswer().toLowerCase());
            return "registered";
        }
        catch (DuplicateKeyException e){
            throw new Exception("phoneNumber exist");
        }
        catch (DataIntegrityViolationException e){
            throw new Exception("fields should not be empty");
        }
        catch (Exception e)
        {
            throw new Exception("Registration failed");
        }
    }

    /**
     * sigIn user by validation
     * @param signInModel to validate the user
     * @return response message
     * @throws Exception
     */
    @Override
    public String signIn(SignInModel signInModel) throws Exception {
        try {
            Integer result = jdbcTemplate.queryForObject("select count(*) from  register where phone_number=? and password=?", Integer.class,
                    signInModel.getPhoneNumber(), signInModel.getPassword());

            if (result == null || result == 0) {
                throw new Exception("invalid phoneNumber or Password");
            }
        }catch (Exception e)
        {
            throw new Exception("Registration failed");
        }
        return "registered";
    }
    /**
     * reset the credential details of the user
     * @param phoneNumber to which details should be updated
     * @param credentials to validate user and update
     * @return response message
     * @throws Exception
     */
    @Override
    public String update(Long phoneNumber, Map<String, Object> credentials) throws Exception {
        if(validateOTP(credentials.get("OTP").toString())){
            credentials.forEach((k,v)->{
                if(!k.equals("OTP"))
                    jdbcTemplate.update("update register set "+k+" =? where phone_number=?",v,phoneNumber);
            });

        }
        else {
            throw new Exception("authentication failed");
        }
        return "password Updated";
    }


    public PasswordResetResponseDto sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto) {

        PasswordResetResponseDto passwordResetResponseDto = null;
        try {
            PhoneNumber to = new PhoneNumber(passwordResetRequestDto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
            String otp = generateOTP();
            String otpMessage = "Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            Message message = Message
                    .creator(to, from,
                            otpMessage)
                    .create();
            this.otp= otp;
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.DELIVERED, otpMessage);
        } catch (Exception ex) {
            passwordResetResponseDto = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
        }
        return passwordResetResponseDto;
    }

    private boolean validateOTP(String userInputOtp) {
        return this.otp.equals(userInputOtp);
    }

    //6 digit otp
    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}