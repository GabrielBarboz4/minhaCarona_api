package com.minhaCarona.controller;

import com.minhaCarona.model.Cars;
import com.minhaCarona.model.CarsData;
import com.minhaCarona.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private final CarService service;

    public CarsController(CarService service) {
        this.service = service;
    }

    @PostMapping("/registerVehicle")
    public ResponseEntity<Cars> registerVehicle(@RequestBody CarsData vehicleRecord){
        Cars cars =  service.registerVehicle(vehicleRecord);

        return ResponseEntity.ok(cars);
    }

    @GetMapping()
    public List<Cars> findAll() {
        return service.findCars();
    }

    @DeleteMapping({"/{carId}"})
    public ResponseEntity <Void> deleteVehicle(@PathVariable Long carId) {
        service.deleteById(carId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{carId}")
    public ResponseEntity <Cars> editVehicle (@PathVariable Long carId, @RequestBody CarsData vehicleRecord) {
        try {
            service.editVehicle(carId, vehicleRecord);
            return ResponseEntity.ok(new Cars());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{carModel}")
    public ResponseEntity <Cars> findCarByName (@PathVariable Cars cars) {
        try {
            service.findCarByName(cars.getCarModel());
            return ResponseEntity.ok(cars);
        } catch (RuntimeException e)
        {
            throw new RuntimeException("Error finding car by name" + e.getMessage(), e);
        }
    }
}
