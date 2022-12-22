package foodapp.service.user;

import foodapp.model.FilterModel;
import foodapp.model.Restaurants;

import java.util.List;

public interface FilterInterface {

    List<String> viewSortOptions();

    List<String> otherFilters();

    List<Restaurants> filterAll(FilterModel filterModel, int pageSize, int pageNumber);

    List<String> showPaymentType();
}
