package com.example.VehicleManagement.Repositories;

import com.example.VehicleManagement.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
    Optional<Vehicle> findByChassisNumber(String chassisNumber);
    Optional<Vehicle> findByPlateNumber_PlateNumber(String plateNumber);
    Optional<Vehicle> findByOwner_NationalId(String nationalId);
}
