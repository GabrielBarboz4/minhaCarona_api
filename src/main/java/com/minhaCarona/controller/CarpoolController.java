package com.minhaCarona.controller;

import com.minhaCarona.model.Carpool;
import com.minhaCarona.model.CarpoolData;
import com.minhaCarona.service.CarpoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carpool")
public class CarpoolController {

    @Autowired
    private final CarpoolService service;


    public CarpoolController(CarpoolService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Carpool> registerCarpool (@RequestBody CarpoolData carpoolData) {
        try {
            Carpool registeredCarpool = service.registerCarpool(carpoolData);
            return ResponseEntity.ok(registeredCarpool);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Carpool> findCarpool () {
        return service.findRegisterCarpool();
    }

    @DeleteMapping("/{carpoolId}")
    public ResponseEntity<Void> deleteCarpool (@PathVariable Long carpoolId) {
        service.deleteCarpool(carpoolId);
        return ResponseEntity.noContent().build();
    }


}
