package com.onlineBookStore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Books {
    private String BookID;
    private String BookName;
    private int QUANTITY;
    private float bookPrice;
    private String BookAuthor;
}
