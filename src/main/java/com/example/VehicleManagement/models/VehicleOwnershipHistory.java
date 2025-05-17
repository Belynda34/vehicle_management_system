package com.example.VehicleManagement.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class VehicleOwnershipHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private VehicleOwner owner;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private double purchasePrice;
}
