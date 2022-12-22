package com.evaluation.foodapp.service;
import com.evaluation.foodapp.config.TwilioConfiguration;
import com.evaluation.foodapp.payload.SmsRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service("twilio")
public class TwilioSmsSender implements SmsSender {

    private String otp;
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }


    @Override
    public String sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());


//            String message = smsRequest.getMessage();
            String otp=generateOTP();
            String otpMessage="Dear Customer , Your OTP is ##" + otp + "##. Use this Passcode to complete your transaction. Thank You.";
            Message message = Message.creator(to, from, otpMessage).create();
            this.otp=otp;
            return String.valueOf(com.evaluation.foodapp.model.OtpStatus.DELIVERED);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
            );
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }

    public String  validateOTP(String userInputOtp) {
        if(this.otp.equals(userInputOtp)){
            return String.valueOf(com.evaluation.foodapp.model.OtpStatus.VALIDATED);
        }
        return String.valueOf(com.evaluation.foodapp.model.OtpStatus.VALIDATION_FAILED);
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
}