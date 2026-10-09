package com.popupsbyshivani.workshops.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(optional=false) private Booking booking;
    @NotNull @DecimalMin("0.0") private Double amount;
    @NotBlank private String method = "UPI";
    @NotBlank private String status = "PENDING";
    private LocalDate paymentDate;
    public Payment() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Booking getBooking(){return booking;} public void setBooking(Booking booking){this.booking=booking;}
    public Double getAmount(){return amount;} public void setAmount(Double amount){this.amount=amount;}
    public String getMethod(){return method;} public void setMethod(String method){this.method=method;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
    public LocalDate getPaymentDate(){return paymentDate;} public void setPaymentDate(LocalDate paymentDate){this.paymentDate=paymentDate;}
}
