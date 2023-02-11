package com.example.finalproject.mapper;

import com.example.finalproject.domain.Medicines;
import com.example.finalproject.domain.dto.MedicinesDto;
import com.example.finalproject.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicinesMapper {

    @Autowired
    private PatientRepository patientRepository;

    public MedicinesDto mapToMedicinesDto(final Medicines medicines) {
        return new MedicinesDto(medicines.getId(),
                medicines.getPrice(),
                medicines.getName(),
                medicines.getQuantity(),
                medicines.getPatient().getId());
    }

    public Medicines mapToMedicines(final MedicinesDto medicinesDto) throws Exception {
        return new Medicines(medicinesDto.getId(),
                medicinesDto.getPrice(),
                medicinesDto.getName(),
                medicinesDto.getQuantity(),
                patientRepository.findById(medicinesDto.getPatientId()).orElseThrow(Exception::new));
    }

    public List<MedicinesDto> mapToMedicinesDtoList(final List<Medicines> medicines){
        return medicines.stream()
                .map(this::mapToMedicinesDto)
                .collect(Collectors.toList());
    }
}
