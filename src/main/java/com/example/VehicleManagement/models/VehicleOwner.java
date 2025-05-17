package com.example.VehicleManagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String nationalId;

    @Column(unique = false)
    private String phone;

    private String address;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PlateNumber> plateNumbers;

    @OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VehicleOwnershipHistory> ownershipHistories;
}
