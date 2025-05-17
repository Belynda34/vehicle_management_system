package com.example.VehicleManagement.Repositories;

import com.example.VehicleManagement.models.VehicleOwner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner,Long> {
    Optional <VehicleOwner> findByNationalId(String nationalId);
    Optional <VehicleOwner> findByPhone(String phone);


//    List<VehicleOwner> findAll();
}
