package com.popupsbyshivani.workshops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotBlank private String fullName;
    @NotBlank @Email @Column(unique=true) private String email;
    @NotBlank private String phone;
    public Participant() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getFullName(){return fullName;} public void setFullName(String fullName){this.fullName=fullName;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
}
