package com.minhaCarona.service;

import com.minhaCarona.exceptions.VehicleNotFoundException;
import com.minhaCarona.model.Cars;
import com.minhaCarona.dto.CarsData;
import com.minhaCarona.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private final CarsRepository carsRepository;

    public CarService ( CarsRepository carsRepository ) {
        this.carsRepository = carsRepository;
    }

    public Cars registerVehicle ( CarsData vehicleRecord ) {
        return carsRepository.save(new Cars( vehicleRecord ));
    }

    public List<Cars> findCars() {
        return carsRepository.findAll();
    }

    public void deleteById ( Long carId ) {
        Optional<Cars> carFinder = carsRepository.findById( carId );

        if (carFinder.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle not found with ID: " + carId);
        }

        carsRepository.deleteById(carId);
    }

    public Cars editVehicle ( Long carId, CarsData vehicleRecord ) {

        return carsRepository.findById(carId)
                .map(existingVehicle -> {
                    existingVehicle.setCarModel(vehicleRecord.carModel());
                    existingVehicle.setNumberOfSeats(vehicleRecord.numberOfSeats());
                    existingVehicle.setPlate(vehicleRecord.plate());
                    return carsRepository.save(existingVehicle);
                })
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with ID: " + carId));

    }

    public List < Cars > findCarByName ( String carModel ) {

        List <Cars> listOfCars = carsRepository.findCarModel(carModel);
        if (listOfCars.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle " + carModel + " not found");
        }

        return listOfCars;
    }
}
