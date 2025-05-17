package com.example.VehicleManagement.PlateNumber;

import com.example.VehicleManagement.Repositories.PlateRepository;
import com.example.VehicleManagement.Repositories.VehicleOwnerRepository;
import com.example.VehicleManagement.models.PlateNumber;
import com.example.VehicleManagement.models.Vehicle;
import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlateService {
    private final PlateRepository plateRepository;
    private final VehicleOwnerRepository vehicleOwnerRepository;

    public ApiResponse<PlateNumber> registerPlateNumber(PlateDTO dto){
        try{
            log.info("Registering plate number: {}", dto.getPlateNumber());
            VehicleOwner owner =  vehicleOwnerRepository.findById(dto.getId()).orElseThrow();
            boolean plateExists = plateRepository.findByPlateNumber(dto.getPlateNumber()).isPresent();
            if(plateExists){
                return new ApiResponse<>(false,"Plate number already exists ",null);
            }

            var plate = new PlateNumber();
            plate.setPlateNumber(dto.getPlateNumber());
            plate.setOwner(owner);
            plate.setIssuedDate(LocalDate.now());
            plate.setInUse(false);
            plateRepository.save(plate);
            return new ApiResponse<>(true,"Plate Number created",plate);
        }catch (Exception e){
            System.out.println("Error in creating vehicle owner"+e.getMessage());
            return new ApiResponse<>(false,"Plate number not created",null);
        }

    }


    public ApiResponse<List<PlateNumber>> getPlateNumberByOwner (Long id){
        try{
            List<PlateNumber> plateNumbers = plateRepository.findByOwnerId(id);
            if(plateNumbers.isEmpty()){
                return new ApiResponse<>(false,"No Plate numbers found for the owner",null);
            }
            return  new ApiResponse<>(true,"Plate numbers",plateNumbers);
        }catch(Exception e) {
            System.out.println("Error in fetching plate number"+e.getMessage());
            return new ApiResponse<>(false,"An error occured",null);
        }
    }
}
