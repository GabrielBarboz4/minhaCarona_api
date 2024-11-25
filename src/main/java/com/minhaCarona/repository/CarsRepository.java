package com.minhaCarona.repository;

import com.minhaCarona.model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarsRepository extends JpaRepository<Cars, Long> {
    Optional<Cars> findByCarModelContainingIgnoreCase(String carModel);
}
