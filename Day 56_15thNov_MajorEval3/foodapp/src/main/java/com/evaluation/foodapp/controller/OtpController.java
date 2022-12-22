package com.evaluation.foodapp.controller;

import com.evaluation.foodapp.request.OtpRequest;
import com.evaluation.foodapp.response.OtpResponse;
import com.evaluation.foodapp.service.EmailOtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class OtpController {

    @Autowired
    private EmailOtpService emailService;
//    @Autowired
//    private OtpDao daoService;


    //This will run when employee will hit forget password button
    //Send OTP through mail to change password
    @RequestMapping(value = "/employee/otpMail", method = RequestMethod.PUT)
    public ResponseEntity<Object> send2faCodeinEmail(@RequestBody OtpRequest otpRequest) throws Exception {
        String twoFaCode = String.valueOf(new Random().nextInt(9999)+1000);

        emailService.sendEmail(otpRequest.getEmailId(),twoFaCode);
        emailService.update2FAProperties(otpRequest,twoFaCode);

        return new ResponseEntity<>(HttpStatus.OK);
    }



    //This is Entering OTP by employee

    @RequestMapping(value="/employee/checkCode", method=RequestMethod.PUT)
    public ResponseEntity<Object> verify(@RequestBody OtpResponse otpResponse) {
        ///String empID = authentication.getName();
        String customer_id = otpResponse.getCustomer_id();
        boolean isValid = emailService.checkCode(otpResponse,customer_id);

        if(isValid){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

}
