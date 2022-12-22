package foodapp.service.restaurant;

import foodapp.exception.SessionIdExpiredException;
import foodapp.model.Categories;

import java.util.List;

public interface CategoryInterface {

    String addCategories(List<Categories> categories) throws SessionIdExpiredException;

    String changeDeliveryCharge(int deliveryCharge , String categoryName) throws SessionIdExpiredException;

    String removeCategory(String categoryName) throws SessionIdExpiredException;

    List<Categories> viewCategories() throws SessionIdExpiredException;

    Categories getParticularCategory(String categoryName) throws SessionIdExpiredException;

}
