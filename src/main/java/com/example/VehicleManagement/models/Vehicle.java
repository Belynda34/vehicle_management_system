package com.example.VehicleManagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chassisNumber;
    private String manufacturer;
    private int manufacturerYear;
    private BigDecimal price;
    private String modelName;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<PlateNumber> plateNumbers;
}
