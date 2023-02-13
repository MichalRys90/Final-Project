package com.example.finalproject.controller;

import com.example.finalproject.domain.Medicines;
import com.example.finalproject.domain.dto.MedicinesDto;
import com.example.finalproject.exception.PatientWithGivenIdDoesntExist;
import com.example.finalproject.mapper.MedicinesMapper;
import com.example.finalproject.service.MedicinesDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/medicines")
public class MedicinesController {

    @Autowired
    private MedicinesDbService medicinesDbService;
    @Autowired
    private MedicinesMapper medicinesMapper;


    @PostMapping(value = "addMedicine")
    public ResponseEntity<MedicinesDto> addMedicine(@RequestBody MedicinesDto medicinesDto) throws PatientWithGivenIdDoesntExist {
        Medicines medicines = medicinesMapper.mapToMedicines(medicinesDto);
        medicinesDbService.saveMedicine(medicines);
        return ResponseEntity.ok(medicinesMapper.mapToMedicinesDto(medicines));
    }

    @GetMapping(value = "GetAllMedicines")
    public ResponseEntity<List<MedicinesDto>> getMedicines(@RequestParam Long id) throws PatientWithGivenIdDoesntExist {
        List<Medicines> medicines = medicinesDbService.findAllMedicines(id);
        return ResponseEntity.ok(medicinesMapper.mapToMedicinesDtoList(medicines));
    }

    @DeleteMapping(value = "DeleteMedicines/{patientId}/{medicineName}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long patientId, @PathVariable String medicineName) throws PatientWithGivenIdDoesntExist {
        medicinesDbService.removeMedicine(patientId, medicineName);
        return ResponseEntity.ok().build();
    }
}
