package com.minhaCarona.model;


import com.minhaCarona.dto.CarsData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "cars")
public class Cars {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long carId;
    private String carModel;
    @Column(unique = true)
    private String plate;
    private Integer numberOfSeats;

    public Cars (CarsData data) {
        this.carModel = data.carModel();
        this.plate = data.plate();
        this.numberOfSeats = data.numberOfSeats();
    }
}