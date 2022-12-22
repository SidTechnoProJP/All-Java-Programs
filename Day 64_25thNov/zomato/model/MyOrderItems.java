package zomatomodified.zomato.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyOrderItems {

    private String orderId;

    private String itemId;

    private String categoryId;

    private String categoryName;

    private String itemName;

    private int numberOfItems;

}
