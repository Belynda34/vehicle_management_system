package com.example.VehicleManagement.Owner;


import com.example.VehicleManagement.Repositories.VehicleOwnerRepository;
import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.utils.ApiResponse;
import com.example.VehicleManagement.utils.PaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerService {

    private final VehicleOwnerRepository ownerRepository;

    public ApiResponse<VehicleOwner> createOwner(OwnerDTO dto) {
        if(ownerRepository.findByNationalId(dto.getNationalId()).isPresent())
            return new  ApiResponse<>(false,"National Id taken",null);
        if(ownerRepository.findByPhone(dto.getPhone()).isPresent())
            return new  ApiResponse<>(false,"Phone number taken",null);

        try{
            log.info("Registering owner with national Id: {}",dto.getNationalId());
            var owner = new VehicleOwner();
            owner.setName(dto.getName());
            owner.setPhone(dto.getPhone());
            owner.setNationalId(dto.getNationalId());
            owner.setAddress(dto.getAddress());
            ownerRepository.save(owner);
            return new ApiResponse<>(true,"Vehicle owner created successfully",owner);
        }catch (Exception e){
            System.out.println("Error in creating vehicle owner"+e.getMessage());
            return new ApiResponse<>(false,"Vehicle owner not created",null);
        }

    }


    public ApiResponse<List <VehicleOwner>> getAllOwners() {
        try{
            log.info("Fetching all owners with pagination");
            List<VehicleOwner> owners= ownerRepository.findAll();
            return new ApiResponse<>(true,"Vehicle Owners",owners);
        }catch (Exception e){
            log.error("Error in fetching vehicle owners: {}",e.getMessage(),e);
            return new ApiResponse<>(false,"Error in fetching owners",null);
        }
    }

    public ApiResponse <VehicleOwner> getByNationalId(String nationalId){
        try{
            if(!ownerRepository.findByNationalId(nationalId).isPresent())
                return new  ApiResponse<>(false,"National id not found",null);
            VehicleOwner owner = ownerRepository.findByNationalId(nationalId).orElseThrow(() -> new RuntimeException("No vehicle found  with nationalId "+nationalId));
            return new ApiResponse<>(true,"Vehicle Owner By nationalId",owner);
        }catch (Exception e){
            System.out.println("Error in getting vehicle owner"+e.getMessage());
            return new ApiResponse<>(false,"",null);
        }
    }

    public ApiResponse <VehicleOwner> getByPhone(String phone){
        try{
            if(!ownerRepository.findByPhone(phone).isPresent())
                return new  ApiResponse<>(false,"Phone not found",null);
            VehicleOwner owner = ownerRepository.findByPhone(phone).orElseThrow(() -> new RuntimeException("No vehicle found  with Phone "+phone));
            return new ApiResponse<>(true,"Vehicle Owner By Phone number",owner);
        }catch (Exception e){
            System.out.println("Error in getting vehicle owner"+e.getMessage());
            return new ApiResponse<>(false,"Error in getting vehicle owner",null);
        }
    }
}
