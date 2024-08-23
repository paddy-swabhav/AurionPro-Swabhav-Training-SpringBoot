package com.techlab.bankdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Payment;
import com.techlab.bankdb.entity.PaymentMode;
import com.techlab.bankdb.entity.PaymentStatus;
import com.techlab.bankdb.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public ResponseEntity<PageResponse<Payment>> getAllPayments(@Valid
                                                                @RequestParam(required = false) Integer pageNo,
                                                                @Valid
                                                                @RequestParam(required = false) Integer pageSize,
                                                                @Valid
                                                                @RequestParam(required = false) PaymentStatus status,
                                                                @Valid
                                                                @RequestParam(required = false) PaymentMode mode) {

        pageNo = (pageNo == null) ? 0 : pageNo;
        pageSize = (pageSize == null) ? 10 : pageSize;

        if (status != null) {
            return ResponseEntity.ok(paymentService.getPaymentsByStatus(status, pageNo, pageSize));
        }
        
        if (mode != null) {
            return ResponseEntity.ok(paymentService.getPaymentsByMode(mode, pageNo, pageSize));
        }

        return ResponseEntity.ok(paymentService.getAllPayments(pageNo, pageSize));
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<Payment> getPaymentById(@Valid @PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @PostMapping("/payments")
    public Payment addUpdatePayment(@Valid @RequestBody Payment payment) {
        return paymentService.addUpdatePayment(payment);
    }

    @PutMapping("/payments")
    public Payment updatePayment(@Valid @RequestBody Payment payment) {
        return paymentService.addUpdatePayment(payment);
    }

    @DeleteMapping("/payments/{paymentId}")
    public String deletePayment(@Valid @PathVariable int paymentId) {
        paymentService.deletePaymentById(paymentId);
        return "Payment deleted successfully";
    }
}
