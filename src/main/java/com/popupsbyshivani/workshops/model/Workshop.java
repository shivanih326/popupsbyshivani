package com.popupsbyshivani.workshops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Workshop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String title;
    @NotBlank private String activity;
    @NotBlank private String venue;
    @NotNull private LocalDate eventDate;
    @NotNull @DecimalMin("0.0") private Double fee;
    @NotBlank private String status = "UPCOMING";

    public Workshop() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getActivity(){return activity;} public void setActivity(String activity){this.activity=activity;}
    public String getVenue(){return venue;} public void setVenue(String venue){this.venue=venue;}
    public LocalDate getEventDate(){return eventDate;} public void setEventDate(LocalDate eventDate){this.eventDate=eventDate;}
    public Double getFee(){return fee;} public void setFee(Double fee){this.fee=fee;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
