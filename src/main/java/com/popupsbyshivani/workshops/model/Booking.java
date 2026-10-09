package com.popupsbyshivani.workshops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(optional=false) private Participant participant;
    @ManyToOne(optional=false) private Workshop workshop;
    @NotBlank private String status = "CONFIRMED";
    private LocalDateTime bookedAt = LocalDateTime.now();
    public Booking() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Participant getParticipant(){return participant;} public void setParticipant(Participant participant){this.participant=participant;}
    public Workshop getWorkshop(){return workshop;} public void setWorkshop(Workshop workshop){this.workshop=workshop;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public LocalDateTime getBookedAt(){return bookedAt;} public void setBookedAt(LocalDateTime bookedAt){this.bookedAt=bookedAt;}
}
