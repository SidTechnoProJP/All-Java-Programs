package com.majorEvaluation.foodApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    @Column(columnDefinition ="varchar(30)",nullable = false)
    @NotBlank(message = "enter the restaurant ID")
    private String restaurantId;



}
