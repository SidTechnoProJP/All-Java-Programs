package foodapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCategory {

    private String categoryId;

    private String  menuId;

    private String restaurantId;

    private String categoryName;

    private String isDeleted = "false";

}
