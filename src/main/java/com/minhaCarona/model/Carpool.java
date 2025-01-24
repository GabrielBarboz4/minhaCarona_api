package com.minhaCarona.model;

import com.minhaCarona.dto.CarpoolData;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "carpool")
public class Carpool {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long carpoolId;
    private String tenantName;
    @ManyToOne
    @JoinColumn(name = "carId")
    private Cars cars;
    private LocalDate startCarpool;
    private LocalDate finalCarpool;

    public Carpool (CarpoolData carpoolData, Cars car) {
        this.tenantName = carpoolData.tenantName();
        this.cars = car;
        this.startCarpool = carpoolData.startCarpool();
        this.finalCarpool = carpoolData.finalCarpool();
    }
}
