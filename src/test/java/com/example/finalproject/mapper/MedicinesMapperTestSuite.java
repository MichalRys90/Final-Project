package com.example.finalproject.mapper;

import com.example.finalproject.domain.Medicines;
import com.example.finalproject.domain.Patient;
import com.example.finalproject.domain.dto.MedicinesDto;
import com.example.finalproject.exception.PatientWithGivenIdDoesntExist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class MedicinesMapperTestSuite {

    @Autowired
    private MedicinesMapper medicinesMapper;

    Medicines medicines = new Medicines(1, new BigDecimal(2), "name", 2, new Patient());

    @Test
    void testMapToDto() {
        //Given & When
        MedicinesDto medicinesDto = medicinesMapper.mapToMedicinesDto(medicines);

        //Then
        assertEquals("name", medicinesDto.getName());
    }
}
