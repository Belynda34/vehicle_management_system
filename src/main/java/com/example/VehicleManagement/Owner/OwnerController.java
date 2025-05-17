package com.example.VehicleManagement.Owner;

import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @PostMapping("/create")
    public ApiResponse<VehicleOwner> createVehicleOwner (@RequestBody OwnerDTO dto){
        return this.ownerService.createOwner(dto);
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<VehicleOwner>>> getAllVehicleOwners(){
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @GetMapping("/search/nationalId")
    public ApiResponse<VehicleOwner> getByNationalId(@RequestParam String nationalId){
        return this.ownerService.getByNationalId(nationalId);
    }



    @GetMapping("/search/phone")
    public ApiResponse<VehicleOwner> getByPhone(@RequestParam String phone){
        return this.ownerService.getByPhone(phone);
    }

}
