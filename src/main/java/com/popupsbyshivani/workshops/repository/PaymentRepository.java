package com.popupsbyshivani.workshops.repository;
import com.popupsbyshivani.workshops.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepository extends JpaRepository<Payment, Long> {}
