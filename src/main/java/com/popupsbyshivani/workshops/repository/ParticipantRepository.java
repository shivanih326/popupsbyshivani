package com.popupsbyshivani.workshops.repository;
import com.popupsbyshivani.workshops.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ParticipantRepository extends JpaRepository<Participant, Long> {}
