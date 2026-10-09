package com.popupsbyshivani.workshops.controller;
import com.popupsbyshivani.workshops.model.*;
import com.popupsbyshivani.workshops.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate; import java.util.List;
@RestController @RequestMapping("/api/payments")
public class PaymentController {
 private final PaymentRepository payments; private final BookingRepository bookings;
 public PaymentController(PaymentRepository p,BookingRepository b){payments=p;bookings=b;}
 @GetMapping public List<Payment> all(){return payments.findAll();}
 @PostMapping public Payment create(@Valid @RequestBody Payment p){if(p.getBooking()==null||p.getBooking().getId()==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"booking.id is required");p.setId(null);p.setBooking(bookings.findById(p.getBooking().getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found")));if(p.getStatus()==null||p.getStatus().isBlank())p.setStatus("PENDING");if("PAID".equalsIgnoreCase(p.getStatus())&&p.getPaymentDate()==null)p.setPaymentDate(LocalDate.now());return payments.save(p);}
 @PatchMapping("/{id}/status") public Payment status(@PathVariable Long id,@RequestParam String value){Payment p=payments.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found"));p.setStatus(value);if("PAID".equalsIgnoreCase(value))p.setPaymentDate(LocalDate.now());return payments.save(p);}
}
