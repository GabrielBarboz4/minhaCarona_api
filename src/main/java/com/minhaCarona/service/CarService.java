package com.minhaCarona.service;


import com.minhaCarona.model.Cars;
import com.minhaCarona.model.CarsData;
import com.minhaCarona.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private final CarsRepository carsRepository;

    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Cars registerVehicle(CarsData vehicleRecord) {
        return carsRepository.save(new Cars(vehicleRecord));
    }

    public List<Cars> findCars() {
        return carsRepository.findAll();
    }

    public void deleteById(Long carId) {
        carsRepository.deleteById(carId);
    }

    public Cars editVehicle(Long carId, CarsData vehicleRecord) {

        try {
            Cars existingVehicle = carsRepository.findById(carId).orElseThrow(() -> new RuntimeException("Vehicle not found" + carId));

            existingVehicle.setCarModel(vehicleRecord.carModel());
            existingVehicle.setPlate(vehicleRecord.plate());
            existingVehicle.setNumberOfSeats(vehicleRecord.numberOfSeats());
            return carsRepository.save(existingVehicle);

        } catch (RuntimeException e) {
            throw new RuntimeException("Error while editing vehicle: " + e.getMessage(), e);
        }
    }

    public Optional<Cars> findCarByName(String carModel) {
        try {
            return carsRepository.findByCarModelContainingIgnoreCase(carModel);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding car");
        }
    }
}
