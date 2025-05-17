package com.example.VehicleManagement.Owner;

import com.example.VehicleManagement.models.PlateNumber;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "National ID is required")
    private String nationalId;
    @NotBlank(message = "Phone number is required")
    private String phone;
    private String address;
}
