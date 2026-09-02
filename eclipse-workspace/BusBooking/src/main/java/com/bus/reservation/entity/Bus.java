package com.bus.reservation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "buses")
public class Bus {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String busNumber;
    private String source;
    private String destination;
    private String busType;
    private Integer totalSeats;
    private Double fare;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public Bus() {}

    public Long getId() { return id; }
    public String getBusNumber() { return busNumber; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getBusType() { return busType; }
    public Double getFare() { return fare; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    
}