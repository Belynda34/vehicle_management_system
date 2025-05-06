package com.example.VehicleManagement.Owner;


import com.example.VehicleManagement.Repositories.VehicleOwnerRepository;
import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final VehicleOwnerRepository ownerRepository;

    public ApiResponse<VehicleOwner> createOwner(OwnerDTO dto) {
        if(ownerRepository.findByNationalId(dto.getNationalId()).isPresent())
            return new  ApiResponse<>(false,"National Id taken",null);
        if(ownerRepository.findByPhone(dto.getPhone()).isPresent())
            return new  ApiResponse<>(false,"Phone number taken",null);

        try{
            var owner = new VehicleOwner();
            owner.setName(dto.getName());
            owner.setPhone(dto.getPhone());
            owner.setNationalId(dto.getNationalId());
            owner.setAddress(dto.getAddress());
            ownerRepository.save(owner);
            return new ApiResponse<>(true,"Vehicle owner created successfully",owner);
        }catch (Exception e){
            System.out.println("Error in creating vehicle owner"+e.getMessage());
            return new ApiResponse<>(false,"Vehicle owner not creatd",null);
        }

    }



}
