package com.example.VehicleManagement.Repositories;

import com.example.VehicleManagement.models.VehicleOwnershipHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleOwnershipHistoryRepository extends JpaRepository<VehicleOwnershipHistory,Long> {
    List<VehicleOwnershipHistory> findByVehicleId(Long vehicleId);
    List<VehicleOwnershipHistory> findByVehicle_PlateNumber_PlateNumber(String plateNumber);
    List<VehicleOwnershipHistory> findByVehicle_ChassisNumber(String chassisNumber);
    Optional<VehicleOwnershipHistory> findByVehicleIdAndEndDateIsNull(Long vehicleId);
}
