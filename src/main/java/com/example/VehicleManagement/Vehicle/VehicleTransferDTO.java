package com.example.VehicleManagement.Vehicle;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VehicleTransferDTO {
    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "New owner ID is required")
    private Long newOwnerId;

    @NotNull(message = "New plate number ID is required")
    private Long newPlateNumberId;

    @Positive(message = "Purchase price must be positive")
    private double purchasePrice;
}
