package com.danish.bookstore_backend.repositories;

import com.danish.bookstore_backend.entities.Payment;
import com.danish.bookstore_backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByPaymentHolder(Person paymentHolder);
}
