package com.example.VehicleManagement.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String plateNumber;

    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private VehicleOwner owner;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
