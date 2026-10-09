package com.popupsbyshivani.workshops.controller;
import com.popupsbyshivani.workshops.model.Participant;
import com.popupsbyshivani.workshops.repository.ParticipantRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
@RestController @RequestMapping("/api/participants")
public class ParticipantController {
 private final ParticipantRepository repo;
 public ParticipantController(ParticipantRepository repo){this.repo=repo;}
 @GetMapping public List<Participant> all(){return repo.findAll();}
 @PostMapping public Participant create(@Valid @RequestBody Participant p){p.setId(null);return repo.save(p);}
 @PutMapping("/{id}") public Participant update(@PathVariable Long id,@Valid @RequestBody Participant p){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Participant not found");p.setId(id);return repo.save(p);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Participant not found");repo.deleteById(id);}
}
