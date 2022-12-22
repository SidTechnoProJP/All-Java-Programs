package com.evaluation.foodapp.controller;

import com.evaluation.foodapp.exception.*;
import com.evaluation.foodapp.model.Bill;
import com.evaluation.foodapp.model.Category;
import com.evaluation.foodapp.service.BillService;
import com.evaluation.foodapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evaluation.foodapp.service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    BillService billService;

    @Autowired
    CategoryService catService;

    @PostMapping("/new")
    public ResponseEntity<String> createAdmin() throws AdminException {
        String result = adminService.createNewAdmin();
        return new ResponseEntity<String>(result, HttpStatus.CREATED);
    }

    @PostMapping("/bill/add")
    public ResponseEntity<Bill> generateBill(@RequestParam(required = false) String key,
                                             @RequestParam Integer customerId, @RequestParam Integer orderId)
            throws BillException, CustomerException, OrderDetailsException, LoginException {
        Bill myBill = billService.generateBill(key, customerId, orderId);
        return new ResponseEntity<Bill>(myBill, HttpStatus.CREATED);
    }

    @DeleteMapping("/bill/remove/{billId}")
    public ResponseEntity<Bill> removeBill(@RequestParam(required = false) String key,
                                           @PathVariable("billId") Integer billId) throws BillException, LoginException {
        Bill removedBill = billService.removeBill(key, billId);
        return new ResponseEntity<Bill>(removedBill, HttpStatus.OK);
    }

    @GetMapping("/bill/view/{billId}")
    public ResponseEntity<Bill> viewBill(@RequestParam(required = false) String key,
                                         @PathVariable("billId") Integer billId) throws BillException, LoginException {
        Bill bill = billService.viewBill(key, billId);
        return new ResponseEntity<Bill>(bill, HttpStatus.ACCEPTED);
    }

    //Category

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestParam(required = false) String key,
                                                @RequestParam String categoryName) throws CategoryException, LoginException {
        Category newCategory = catService.addCategory(key, categoryName);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/view")
    public ResponseEntity<Category> getCategoryById(@RequestParam(required = false) String key,
                                                    @RequestParam Integer categoryId) throws CategoryException, LoginException {
        Category category = catService.viewCategoryById(key, categoryId);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Category> removeCategory(@RequestParam(required = false) String key,
                                                   @RequestParam String categoryName) throws CategoryException, LoginException {
        Category removedCategory = catService.removeCategory(key, categoryName);
        return new ResponseEntity<Category>(removedCategory, HttpStatus.OK);
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam(required = false) String key)
            throws CategoryException, LoginException {
        List<Category> categories = catService.viewAllCategory(key);
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}
