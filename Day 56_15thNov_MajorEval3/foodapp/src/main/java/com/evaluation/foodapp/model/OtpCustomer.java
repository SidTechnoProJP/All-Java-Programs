package com.evaluation.foodapp.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class OtpCustomer {

    @Id
    @GeneratedValue
    private int otpNo;
    private String is_2fa_enabled;
    private String twoFaCode;
    private String twoFaExpireTime;
    private String twofa_default_type;


    //
    //2fa_code
    //2fa_expire_time
    //2fa_default_type
}
