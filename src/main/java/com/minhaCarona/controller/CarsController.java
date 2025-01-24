package com.minhaCarona.controller;

import com.minhaCarona.model.Cars;
import com.minhaCarona.dto.CarsData;
import com.minhaCarona.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/cars" )
public class CarsController {

    @Autowired
    private final CarService service;

    public CarsController( CarService service ) {
        this.service = service;
    }

    @PostMapping( "/registerVehicle" )
    public ResponseEntity < Cars > registerVehicle( @RequestBody CarsData vehicleRecord ){
        Cars cars =  service.registerVehicle( vehicleRecord );

        return ResponseEntity.status(HttpStatus.CREATED).body(cars);
    }

    @GetMapping( "/list" )
    public List < Cars > findAll() {
        return service.findCars();
    }

    @DeleteMapping( {"/{carId}"} )
    public ResponseEntity < Void > deleteVehicle( @PathVariable Long carId ) {
        service.deleteById(carId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping( "/{carId}" )
    public ResponseEntity < Cars > editVehicle ( @PathVariable Long carId, @RequestBody CarsData vehicleRecord ) {
          Cars updateCar = service.editVehicle( carId, vehicleRecord );
          return ResponseEntity.ok(updateCar);
    }

    @GetMapping( "/{carModel}" )
    public ResponseEntity < List < Cars >> findCarByModel( @PathVariable String carModel ) {

        List < Cars > carFound = service.findCarByName( carModel );

        return ResponseEntity.ok(carFound);
    }
}
