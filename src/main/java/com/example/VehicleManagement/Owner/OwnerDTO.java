package com.example.VehicleManagement.Owner;

import com.example.VehicleManagement.models.PlateNumber;
import jakarta.persistence.Column;
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
    private String name;
    private String nationalId;
    private String phone;
    private String address;
}
