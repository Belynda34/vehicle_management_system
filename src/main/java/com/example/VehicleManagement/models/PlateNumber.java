package com.example.VehicleManagement.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private LocalDate issuedDate;

    @Column(nullable = false)
    private boolean inUse;

    @ManyToOne
    @JoinColumn(name = "owner_id",nullable = false)
    @JsonIgnore
    private VehicleOwner owner;

    @OneToOne(mappedBy = "plateNumber")
    private Vehicle vehicle;

}
