package com.onlineBookStore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistory {
  String  UserId  ;
  String BookId ;
  int quantity ;
  LocalDate PurchaseDate;
}
