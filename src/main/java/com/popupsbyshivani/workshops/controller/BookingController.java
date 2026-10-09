package com.popupsbyshivani.workshops.controller;
import com.popupsbyshivani.workshops.model.*;
import com.popupsbyshivani.workshops.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/bookings")
public class BookingController {
 private final BookingRepository bookings; private final ParticipantRepository participants; private final WorkshopRepository workshops;
 public BookingController(BookingRepository b,ParticipantRepository p,WorkshopRepository w){bookings=b;participants=p;workshops=w;}
 @GetMapping public List<Booking> all(){return bookings.findAll();}
 @PostMapping public Booking create(@Valid @RequestBody Booking b){
  if(b.getParticipant()==null||b.getParticipant().getId()==null||b.getWorkshop()==null||b.getWorkshop().getId()==null)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"participant.id and workshop.id are required");
  b.setId(null); b.setParticipant(participants.findById(b.getParticipant().getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Participant not found")));
  b.setWorkshop(workshops.findById(b.getWorkshop().getId()).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Workshop not found")));
  if(b.getStatus()==null||b.getStatus().isBlank())b.setStatus("CONFIRMED"); return bookings.save(b);
 }
 @PatchMapping("/{id}/status") public Booking status(@PathVariable Long id,@RequestParam String value){Booking b=bookings.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found"));b.setStatus(value);return bookings.save(b);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!bookings.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Booking not found");bookings.deleteById(id);}
}
