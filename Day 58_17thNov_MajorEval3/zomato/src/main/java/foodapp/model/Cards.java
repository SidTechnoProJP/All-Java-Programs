package foodapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import foodapp.annotation.Card;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cards {

    private String userId;

    @Card
    private long cardNumber;

}
