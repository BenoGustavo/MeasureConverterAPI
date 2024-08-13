package com.api.measureconverter.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.measureconverter.model.ConverterEntity;
import com.api.measureconverter.repositories.ConverterRepository;
import com.api.measureconverter.utils.dto.ConverterDto;
import com.api.measureconverter.utils.enums.ConversionCategories;

@Service
public class ConverterService {

    @Autowired
    private ConverterRepository conversionRepository;

    public ConverterDto convert(double value, String fromUnit, String toUnit, ConversionCategories type) {
        Optional<ConverterEntity> conversion = conversionRepository.findByFromUnitAndToUnitAndType(fromUnit, toUnit,
                type);

        if (conversion.isPresent()) {
            Double result = value * conversion.get().getFactor();

            return new ConverterDto.Builder()
                    .fromUnit(fromUnit)
                    .toUnit(toUnit)
                    .factor(conversion.get().getFactor())
                    .type(type)
                    .result(String.valueOf(result))
                    .build();

        } else {
            throw new IllegalArgumentException("Conversion not supported");
        }
    }

    public List<ConversionCategories> findAllDistinctTypes() {
        return conversionRepository.findAllDistinctTypes();
    }

    public List<ConverterEntity> findByType(ConversionCategories type) {
        return conversionRepository.findByType(type);
    }

    public List<String> findAllDistinctToUnit(ConversionCategories type) {
        return conversionRepository.findAllDistinctToUnit(type);
    }

    public List<String> findAllDistinctFromUnit(ConversionCategories type) {
        return conversionRepository.findAllDistinctFromUnit(type);
    }
}