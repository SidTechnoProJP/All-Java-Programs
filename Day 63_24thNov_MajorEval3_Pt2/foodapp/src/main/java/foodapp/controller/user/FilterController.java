package foodapp.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import foodapp.model.FilterModel;
import foodapp.service.user.FilterInterface;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user/filter")
public class FilterController {

    /**
     * Filter Controller
     */

    @Autowired
    private FilterInterface filterInterface;

    @GetMapping("/showFilters")
    public ResponseEntity<List<String>> viewSortOption() {
        return ResponseEntity.of(Optional.of(filterInterface.viewSortOptions()));
    }

    @GetMapping("/showPaymentType")
    public ResponseEntity<List<String>> viewPaymentType() {
        return ResponseEntity.of(Optional.of(filterInterface.showPaymentType()));
    }

    @GetMapping("/showOtherFilters")
    public ResponseEntity<List<String>> viewOtherFilters() {
        return ResponseEntity.of(Optional.of(filterInterface.otherFilters()));
    }

    @GetMapping("/result")
    public ResponseEntity<List<?>> viewFilteredResult(@RequestBody FilterModel filterModel, @RequestParam int pageSize,
                                                      @RequestParam int pageNumber) {
        return ResponseEntity.of(Optional.of(filterInterface.filterAll(filterModel, pageSize, pageNumber)));
    }

}
