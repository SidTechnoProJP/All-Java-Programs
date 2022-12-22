package com.evaluation.foodapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@NotNull(message = "First name is mandatory")
	private String firstName;
	
	@NotNull(message = "Last name is mandatory")
	private String lastName;
	
	private Integer age;
	private String gender;
	
	@Size(max = 10,min = 10, message = "Required only 10 digits")
	private String mobileNumber;
	
	@NotNull(message = "Email is mandatory")
	@Email(message = "Required in email format")
	private String email;
	
	@NotNull(message = "Password is mandatory")
	private String password;

	//@NotNull
	private String twoFaCode;
	//@NotNull
	private String twoFaExpireTime;

	@JsonIgnore
	@OneToOne(targetEntity = FoodCart.class)
	private FoodCart cart;

//	@JsonIgnore
//	@OneToOne
//	private OtpCustomer otpCustomer;

}