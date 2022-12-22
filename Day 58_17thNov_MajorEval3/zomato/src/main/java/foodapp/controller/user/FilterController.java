package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import foodapp.exception.SessionIdExpiredException;
import foodapp.service.user.FilterInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    private FilterInterface filterInterface;

    @GetMapping("/showSortOption")
    public ResponseEntity<List<String>> viewSortOption() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(filterInterface.viewSortOptions()));
    }

    @GetMapping("/showPaymentType")
    public ResponseEntity<List<String>> viewPaymentType() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(filterInterface.showPaymentType()));
    }

    @GetMapping("/showOtherFilters")
    public ResponseEntity<List<String>> viewOtherFilters() throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(filterInterface.otherFilters()));
    }

    @GetMapping("/result")
    public ResponseEntity<List<?>> viewFilteredResult(@RequestParam String categoryName, @RequestParam String sortBy,
                                                      @RequestParam int priceRange, @RequestParam boolean openNow,
                                                      @RequestParam String paymentType, @RequestParam boolean freeDelivery,
                                                      @RequestParam int pageSize, @RequestParam int pageNumber) throws SessionIdExpiredException {
        return ResponseEntity.of(Optional.of(filterInterface.filterAll(categoryName, sortBy,
                priceRange, openNow, paymentType, freeDelivery, pageSize, pageNumber)));
    }

}
