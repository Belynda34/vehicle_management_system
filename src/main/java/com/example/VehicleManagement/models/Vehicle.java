package com.example.VehicleManagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String chassisNumber;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int manufacturerYear;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String modelName;

    @OneToOne
    @JoinColumn(name = "plate_number_id")
    @JsonIgnore
    private PlateNumber plateNumber;

    @ManyToOne
    @JoinColumn(name="owner_id",nullable = false)
    @JsonIgnore
    @JsonManagedReference
    private VehicleOwner owner;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VehicleOwnershipHistory> ownershipHistories;
}
