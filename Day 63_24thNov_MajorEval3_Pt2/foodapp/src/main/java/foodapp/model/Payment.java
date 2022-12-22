package foodapp.model;

import foodapp.customannotations.Payments;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Payments
    String paymentType;

    String restaurantId;

    private String isDeleted = "false";

}
