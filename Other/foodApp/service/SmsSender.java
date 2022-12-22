package com.majorEvaluation.foodApp.service;

import com.majorEvaluation.foodApp.payload.SmsRequest;

public interface SmsSender {
    String sendSms(SmsRequest smsRequest);
     String  validateOTP(String userInputOtp);
}
