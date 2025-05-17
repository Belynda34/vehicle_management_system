package com.example.VehicleManagement.PlateNumber;


import com.example.VehicleManagement.models.PlateNumber;
import com.example.VehicleManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/plates")
public class PlateController {
    private final PlateService plateService;


    @PostMapping("/register")
    public ApiResponse<PlateNumber> registerPlate(@RequestBody PlateDTO dto){
        return this.plateService.registerPlateNumber(dto);
    }

    @GetMapping("/owner")
    public ApiResponse<List<PlateNumber>> plateNumberByOnwer(@RequestParam Long id){
        return this.plateService.getPlateNumberByOwner(id);
    }


}
