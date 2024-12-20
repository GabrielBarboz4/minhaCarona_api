package com.minhaCarona.service;

import com.minhaCarona.model.Carpool;
import com.minhaCarona.model.CarpoolData;
import com.minhaCarona.model.Cars;
import com.minhaCarona.repository.CarpoolRepository;
import com.minhaCarona.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return carpoolRepository.save(new Carpool(carpoolData, car));
    }

    public List<Carpool> findRegisterCarpool() {
        return carpoolRepository.findAll();
    }

    public void deleteCarpool(Long carpoolId) {
        carpoolRepository.deleteById(carpoolId);
    }
}
