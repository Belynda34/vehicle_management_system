package com.example.VehicleManagement.Vehicle;


import com.example.VehicleManagement.Repositories.PlateRepository;
import com.example.VehicleManagement.Repositories.VehicleOwnerRepository;
import com.example.VehicleManagement.Repositories.VehicleOwnershipHistoryRepository;
import com.example.VehicleManagement.Repositories.VehicleRepository;
import com.example.VehicleManagement.models.PlateNumber;
import com.example.VehicleManagement.models.Vehicle;
import com.example.VehicleManagement.models.VehicleOwner;
import com.example.VehicleManagement.models.VehicleOwnershipHistory;
import com.example.VehicleManagement.utils.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final PlateRepository plateRepository;
    private final VehicleOwnerRepository ownerRepository;

    private final VehicleRepository vehicleRepository;

    private final VehicleOwnershipHistoryRepository historyRepository;

    public ApiResponse<Vehicle> registerVehicle(VehicleDTO dto){
        try{
            log.info("Registering vehicle with chassis number: {}", dto.getChassisNumber());
            VehicleOwner owner = ownerRepository.findById(dto.getOwnerId()).orElseThrow();
            PlateNumber plateNumber = plateRepository.findByPlateNumber(dto.getPlateNumberId()).orElseThrow();

           if(plateNumber.isInUse()){
               return new ApiResponse<>(false,"Plate number is already in use",null);
           }

            var vehicle = new Vehicle();
            vehicle.setChassisNumber(dto.getChassisNumber());
            vehicle.setManufacturer(dto.getManufacturer());
            vehicle.setManufacturerYear(dto.getManufacturerYear());
            vehicle.setPrice(dto.getPrice());
            vehicle.setModelName(dto.getModelName());
            vehicle.setOwner(owner);
            vehicle.setPlateNumber(plateNumber);

            plateNumber.setInUse(true);
            plateRepository.save(plateNumber);

            Vehicle savedVehicle = vehicleRepository.save(vehicle);

            VehicleOwnershipHistory history = new VehicleOwnershipHistory();
            history.setVehicle(savedVehicle);
            history.setOwner(owner);
            history.setStartDate(LocalDate.now());
            history.setPurchasePrice(dto.getPrice());
            historyRepository.save(history);

            return new ApiResponse<>(true,"Vehicle registered successfully",savedVehicle);
        }catch(Exception e){
            System.out.println("Error in creating vehicle"+e.getMessage());
            return new ApiResponse<>(false,"An error occured",null);
        }
    }


    @Transactional
    public ApiResponse<Vehicle> vehicleTransfer(VehicleTransferDTO dto){
        try{
            log.info("Transferring vehicle ID: {} to new owner ID: {}", dto.getVehicleId(), dto.getNewOwnerId());
            VehicleOwner newOwner = ownerRepository.findById(dto.getNewOwnerId()).orElseThrow();
            PlateNumber newPlateNumber = plateRepository.findById(dto.getNewPlateNumberId()).orElseThrow();
            Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId()).orElseThrow();

            if(newPlateNumber.isInUse()){
                return new ApiResponse<>(false,"New Plate number is already in use",null);
            }
            // Mark current plate as unavailable
            PlateNumber currentPlateNumber = vehicle.getPlateNumber();
            currentPlateNumber.setInUse(false);
            plateRepository.save(currentPlateNumber);

            vehicle.setOwner(newOwner);
            vehicle.setPlateNumber(newPlateNumber);
            newPlateNumber.setInUse(true);
            plateRepository.save(newPlateNumber);

            VehicleOwnershipHistory currentHistory = historyRepository.findByVehicleIdAndEndDateIsNull(vehicle.getId()).orElseThrow();
            currentHistory.setEndDate(LocalDate.now());
            historyRepository.save(currentHistory);

            VehicleOwnershipHistory newHistory = new VehicleOwnershipHistory();
            newHistory.setVehicle(vehicle);
            newHistory.setOwner(newOwner);
            newHistory.setStartDate(LocalDate.now());
            newHistory.setPurchasePrice(dto.getPurchasePrice());
            historyRepository.save(newHistory);

            return new  ApiResponse<>(true,"Vehicle transferred",vehicle);

        }catch (Exception e){
            log.error("Error in transfering vehicle: {}",e.getMessage(),e);
            return new ApiResponse<>(false,"Error in fetching owners",null);
        }
    }

    public ApiResponse<Vehicle> getVehicleByChassisNumber(String chassisNumber) {
        try {
            log.info("Searching vehicle by Chassis Number: {}", chassisNumber);
            Vehicle vehicle = vehicleRepository.findByChassisNumber(chassisNumber).orElseThrow();
            if (vehicle == null) {
                return new ApiResponse<>(false, "Vehicle not found", null);
            }
            return new ApiResponse<>(true, "Vehicle details", vehicle);
        } catch (Exception e) {
            System.out.println("Error fetching vehicle: " + e.getMessage());
            return new ApiResponse<>(false, "Error fetching vehicle", null);
        }
    }

    public ApiResponse<Vehicle> getVehicleByPlateNumber(String plateNumber){
        try {
            log.info("Searching vehicle by plate number: {}", plateNumber);
            Vehicle vehicle = vehicleRepository.findByPlateNumber_PlateNumber(plateNumber).orElseThrow();
            return  new ApiResponse<>(true,"Vehicle by Plate Number",vehicle);
        }catch (Exception e){
            log.error("Error in fetching vehicle by plate number: {}",e.getMessage(),e);
            return new ApiResponse<>(false,"Error in fetching vehicles",null);
        }
    }

    public ApiResponse<List<VehicleOwnershipHistory>> getOwnershipHistoryByPlateNumber(String plateNumber){
        try{
            log.info("Fetching ownership histories by plate number: {}", plateNumber);
            List<VehicleOwnershipHistory> histories = historyRepository.findByVehicle_PlateNumber_PlateNumber(plateNumber);
            return new ApiResponse<>(true,"Owner histories",histories);
        }catch (Exception e){

            log.error("Error in fetching ownership histories by plate number: {}",e.getMessage(),e);
            return new ApiResponse<>(false,"Error in fetching ownership histories",null);
        }
    }
//
//    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
//        log.info("Searching vehicle by plate number: {}", plateNumber);
//        return vehicleRepository.findByPlateNumber_PlateNumber(plateNumber);
//    }



//    public ApiResponse<Vehicle> findByOwnerNationalId(String nationalId) {
//        log.info("Searching vehicles by owner national ID: {}", nationalId);
//        VehicleOwner owner = vehicleRepository.findByOwner_NationalId(nationalId);
//    }
//
//    public List<<VehicleOwnershipHistory>> getOwnershipHistoryByChassisNumber(String chassisNumber) {
//        log.info("Fetching ownership history by chassis number: {}", chassisNumber);
//        return historyRepository.findByVehicle_ChassisNumber(chassisNumber);
//    }
//
//    public List<<VehicleOwnershipHistory>> getOwnershipHistoryByPlateNumber(String plateNumber) {
//        log.info("Fetching ownership history by plate number: {}", plateNumber);
//        return historyRepository.findByVehicle_PlateNumber_PlateNumber(plateNumber);
//    }
}
