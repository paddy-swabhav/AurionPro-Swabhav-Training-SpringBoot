package com.techlab.bankdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techlab.bankdb.entity.Payment;
import com.techlab.bankdb.entity.PaymentMode;
import com.techlab.bankdb.entity.PaymentStatus;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Page<Payment> findByPaymentStatus(PaymentStatus status, Pageable pageable);

	Page<Payment> findByPaymentMode(PaymentMode mode, Pageable pageable);


}
