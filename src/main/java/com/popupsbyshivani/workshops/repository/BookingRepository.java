package com.popupsbyshivani.workshops.repository;
import com.popupsbyshivani.workshops.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookingRepository extends JpaRepository<Booking, Long> {}
