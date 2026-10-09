package com.popupsbyshivani.workshops.controller;
import com.popupsbyshivani.workshops.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
 private final WorkshopRepository workshops; private final ParticipantRepository participants; private final BookingRepository bookings; private final PaymentRepository payments;
 public DashboardController(WorkshopRepository w,ParticipantRepository p,BookingRepository b,PaymentRepository pay){workshops=w;participants=p;bookings=b;payments=pay;}
 @GetMapping public Map<String,Long> summary(){return Map.of("workshops",workshops.count(),"participants",participants.count(),"bookings",bookings.count(),"payments",payments.count());}
}
