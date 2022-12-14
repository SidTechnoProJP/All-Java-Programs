package foodapp.service.user;

import foodapp.exception.SessionIdExpiredException;
import foodapp.entity.Restaurants;

import java.util.List;

public interface FilterInterface {

    List<String> viewSortOptions() throws SessionIdExpiredException;

    List<String> otherFilters() throws SessionIdExpiredException;

    List<String> showPaymentType() throws SessionIdExpiredException;

    List<Restaurants> filterAll(String categoryName, String sortBy, int priceRange, boolean openNow, String paymentType, boolean freeDelivery, int pageSize, int pageNumber) throws SessionIdExpiredException;

}
