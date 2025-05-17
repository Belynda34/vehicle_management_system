package com.example.VehicleManagement.Vehicle;


import com.example.VehicleManagement.models.Vehicle;
import com.example.VehicleManagement.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;

    @PostMapping("/register")
    public ApiResponse<Vehicle> registerVehicle(VehicleDTO dto){
        return this.service.registerVehicle(dto);
    }

    @GetMapping("/chassis/{chassisNumber}")
    public ApiResponse<Vehicle> getVehicleDetailsByChasisNumber(@PathVariable String chassisNumber) {
        return this.service.getVehicleByChassisNumber(chassisNumber);
    }

    @GetMapping("/plate/{plateNumber}")
    public ApiResponse<Vehicle> getVehicleDetailsByPlateNumber(@PathVariable String plateNumber) {
        return this.service.getVehicleByChassisNumber(plateNumber);
    }

    @PostMapping("/transfer")
    public ApiResponse<Vehicle> transferVehicle(@Valid @RequestBody VehicleTransferDTO dto){
        return this.service.vehicleTransfer(dto);
    }
}
