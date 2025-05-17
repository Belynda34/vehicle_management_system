package com.example.VehicleManagement.Repositories;

import com.example.VehicleManagement.models.PlateNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlateRepository extends JpaRepository<PlateNumber, Long> {
    List<PlateNumber> findByOwnerId(Long ownerId);
    Optional<PlateNumber> findByPlateNumber(String plateNumber);
}
