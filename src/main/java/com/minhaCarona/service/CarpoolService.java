package com.minhaCarona.service;

import com.minhaCarona.exceptions.CarpoolNotFoundException;
import com.minhaCarona.exceptions.VehicleNotFoundException;
import com.minhaCarona.model.Carpool;
import com.minhaCarona.dto.CarpoolData;
import com.minhaCarona.model.Cars;
import com.minhaCarona.repository.CarpoolRepository;
import com.minhaCarona.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarpoolService {

    @Autowired
    private final CarpoolRepository carpoolRepository;

    @Autowired
    private CarsRepository carsRepository;

    public CarpoolService(CarpoolRepository carpoolRepository) {
        this.carpoolRepository = carpoolRepository;
    }

    public Carpool registerCarpool(CarpoolData carpoolData) {
        Cars car = carsRepository.findById(carpoolData.carId())
                .orElseThrow(() -> new VehicleNotFoundException ("Vehicle not found with ID: " + carpoolData.carId()));

        return carpoolRepository.save(new Carpool(carpoolData, car));
    }

    public List<Carpool> findRegisterCarpool() {
        return carpoolRepository.findAll();
    }

    public void deleteCarpool(Long carpoolId) {

        Carpool carpool = carpoolRepository.findById(carpoolId)
                .orElseThrow(()-> new CarpoolNotFoundException("Carpool not found with ID: " + carpoolId));

        carpoolRepository.delete(carpool);
    }
}
