package com.minhaCarona.repository;

import com.minhaCarona.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface CarsRepository extends JpaRepository < Cars, Long > {

    @Query("SELECT c FROM Cars c WHERE UPPER(c.carModel) LIKE CONCAT('%', UPPER(:carModel), '%')")
    List < Cars > findCarModel( String carModel );
}
