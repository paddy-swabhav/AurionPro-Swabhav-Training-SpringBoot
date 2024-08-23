package com.techlab.bankdb.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.techlab.bankdb.dto.PageResponse;
import com.techlab.bankdb.entity.Payment;
import com.techlab.bankdb.entity.PaymentMode;
import com.techlab.bankdb.entity.PaymentStatus;
import com.techlab.bankdb.exceptions.PaymentNotFoundException;
import com.techlab.bankdb.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public PageResponse<Payment> getAllPayments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Payment> paymentPage = paymentRepository.findAll(pageable);

        return createPageResponse(paymentPage);
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (!payment.isPresent()) {
            throw new PaymentNotFoundException();
        }
        return payment.get();
    }

    @Override
    public Payment addUpdatePayment(Payment payment) {
    	
    	if (payment.getPaymentId()!=0 &&!paymentRepository.existsById(payment.getPaymentId())) {
            throw new PaymentNotFoundException();
        }
    	
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePaymentById(int paymentId) {
        if (!paymentRepository.existsById(paymentId)) {
            throw new PaymentNotFoundException();
        }
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public PageResponse<Payment> getPaymentsByStatus(PaymentStatus status, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Payment> paymentPage = paymentRepository.findByPaymentStatus(status, pageable);

        return createPageResponse(paymentPage);
    }

    @Override
    public PageResponse<Payment> getPaymentsByMode(PaymentMode mode, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Payment> paymentPage = paymentRepository.findByPaymentMode(mode, pageable);

        return createPageResponse(paymentPage);
    }

    private PageResponse<Payment> createPageResponse(Page<Payment> paymentPage) {
        PageResponse<Payment> paymentPageResponse = new PageResponse<>();
        paymentPageResponse.setContent(paymentPage.getContent());
        paymentPageResponse.setSize(paymentPage.getSize());
        paymentPageResponse.setTotalElements(paymentPage.getTotalElements());
        paymentPageResponse.setTotalPages(paymentPage.getTotalPages());
        paymentPageResponse.setLastPage(paymentPage.isLast());

        return paymentPageResponse;
    }
}
