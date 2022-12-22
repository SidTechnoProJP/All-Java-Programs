package com.evaluation.foodapp.service;

import com.evaluation.foodapp.payload.SmsRequest;
import com.evaluation.foodapp.payload.SmsRequest;

public interface SmsSender {


    String sendSms(SmsRequest smsRequest);

    String  validateOTP(String userInputOtp);
}
