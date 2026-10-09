package com.popupsbyshivani.workshops.repository;
import com.popupsbyshivani.workshops.model.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WorkshopRepository extends JpaRepository<Workshop, Long> {}
