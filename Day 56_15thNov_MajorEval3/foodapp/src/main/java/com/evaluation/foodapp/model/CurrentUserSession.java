package com.evaluation.foodapp.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class CurrentUserSession {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer currSessionId;
	
	@NotNull(message = "Email is required")
	@Email
	private String email;	
	
	@NotNull(message = "Date time is required")
	private LocalDateTime loginDateTime;
	
	@NotNull(message = "Role is required")
	private String role;

	private String privateKey;

}
