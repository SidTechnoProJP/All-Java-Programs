package com.evaluation.foodapp.controller.restaurantcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.foodapp.exception.BillException;
import com.evaluation.foodapp.exception.CustomerException;
import com.evaluation.foodapp.exception.LoginException;
import com.evaluation.foodapp.exception.OrderDetailsException;
import com.evaluation.foodapp.model.Bill;
import com.evaluation.foodapp.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping("/add")
    public ResponseEntity<Bill> generateBill(@RequestParam(required = false) String key,
                                             @RequestParam Integer customerId, @RequestParam Integer orderId)
            throws BillException, CustomerException, OrderDetailsException, LoginException {
        Bill myBill = billService.generateBill(key, customerId, orderId);
        return new ResponseEntity<Bill>(myBill, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{billId}")
    public ResponseEntity<Bill> removeBill(@RequestParam(required = false) String key,
                                           @PathVariable("billId") Integer billId) throws BillException, LoginException {
        Bill removedBill = billService.removeBill(key, billId);
        return new ResponseEntity<Bill>(removedBill, HttpStatus.OK);
    }

    @GetMapping("/view/{billId}")
    public ResponseEntity<Bill> viewBill(@RequestParam(required = false) String key,
                                         @PathVariable("billId") Integer billId) throws BillException, LoginException {
        Bill bill = billService.viewBill(key, billId);
        return new ResponseEntity<Bill>(bill, HttpStatus.ACCEPTED);
    }

}
