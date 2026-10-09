package com.popupsbyshivani.workshops.controller;
import com.popupsbyshivani.workshops.model.Workshop;
import com.popupsbyshivani.workshops.repository.WorkshopRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@RestController @RequestMapping("/api/workshops")
public class WorkshopController {
 private final WorkshopRepository repo;
 public WorkshopController(WorkshopRepository repo){this.repo=repo;}
 @GetMapping public List<Workshop> all(){return repo.findAll();}
 @GetMapping("/{id}") public Workshop one(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Workshop not found"));}
 @PostMapping public Workshop create(@Valid @RequestBody Workshop w){w.setId(null);return repo.save(w);}
 @PutMapping("/{id}") public Workshop update(@PathVariable Long id,@Valid @RequestBody Workshop w){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Workshop not found");w.setId(id);return repo.save(w);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Workshop not found");repo.deleteById(id);}
}
