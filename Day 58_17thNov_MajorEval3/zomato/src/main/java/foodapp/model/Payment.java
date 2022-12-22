package foodapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import foodapp.annotation.Payments;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Payments
    String paymentType;

    String restaurantId;
}
