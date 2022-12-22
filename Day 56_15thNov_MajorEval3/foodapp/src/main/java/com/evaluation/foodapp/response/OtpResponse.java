package com.evaluation.foodapp.response;

import lombok.Data;

@Data
public class OtpResponse {
    private String customer_id;
    private String OtpCode;
}
