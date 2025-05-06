package com.example.VehicleManagement.Owner;

import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("/create")
    public ApiResponse<VehicleOwner> createVehicleOwner (@RequestBody OwnerDTO dto){
        return this.ownerService.createOwner(dto);
    }

}
