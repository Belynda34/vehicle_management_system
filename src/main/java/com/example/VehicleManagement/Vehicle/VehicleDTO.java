package com.example.VehicleManagement.Vehicle;

import com.example.VehicleManagement.models.PlateNumber;
import com.example.VehicleManagement.models.Vehicle;
import com.example.VehicleManagement.models.VehicleOwner;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @NotBlank(message = "Chassis number is required")
    private String chassisNumber;
    @NotBlank(message = "Manufacture company is required")
    private String manufacturer;
    @Min(value = 1900, message = "Manufacture year must be after 1900")
    private int manufacturerYear;
    @Positive(message = "Price must be positive")
    private double price;
    @NotBlank(message = "Model name is required")
    private String modelName;
    private String plateNumberId;
    private Long ownerId;
}
