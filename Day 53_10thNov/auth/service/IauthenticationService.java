package com.movieTicketBookingSystem.auth.service;

import com.movieTicketBookingSystem.auth.model.PasswordResetRequestDto;
import com.movieTicketBookingSystem.auth.model.PasswordResetResponseDto;
import com.movieTicketBookingSystem.auth.model.RegistrationModel;
import com.movieTicketBookingSystem.auth.model.SignInModel;

import java.util.Map;

public interface IauthenticationService {
    /**
     * register the user in the database
     * @param registrationModel to store in the database
     * @return response message
     * @throws Exception
     */
    String register(RegistrationModel registrationModel) throws Exception;

    /**
     * sigIn user by validation
     * @param signInModel to validate the user
     * @return response message
     * @throws Exception
     */
    String signIn(SignInModel signInModel) throws Exception;

    /**
     * update credential details of the user
     * @param phoneNumber to which details should be updated
     * @param credentials to validate user and update
     * @return response message
     * @throws Exception
     */
    String update(Long phoneNumber, Map<String,Object> credentials) throws Exception;
    PasswordResetResponseDto sendOTPForPasswordReset(PasswordResetRequestDto passwordResetRequestDto);
}
