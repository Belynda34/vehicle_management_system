package com.example.VehicleManagement.Repositories;

import com.example.VehicleManagement.models.VehicleOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner,Long> {
    Optional <VehicleOwner> findByNationalId(String nationalId);
    Optional <VehicleOwner> findByPhone(String phone);
}
