package com.api.measureconverter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.measureconverter.services.ConverterService;
import com.api.measureconverter.utils.enums.ConversionCategories;

@RestController
@RequestMapping("/api/convert")
public class ConverterController {

    @Autowired
    private final ConverterService conversionService;

    public ConverterController(ConverterService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<Double> convert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            @RequestParam ConversionCategories type) {
        double result = conversionService.convert(value, fromUnit, toUnit, type);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/types")
    public ResponseEntity<?> getTypes() {
        return ResponseEntity.ok(conversionService.findAllDistinctTypes());
    }

    @GetMapping("/byType")
    public ResponseEntity<?> getByType(@RequestParam ConversionCategories type) {
        return ResponseEntity.ok(conversionService.findByType(type));
    }

    @GetMapping("/fromUnits")
    public ResponseEntity<?> getFromUnits(@RequestParam ConversionCategories type) {
        return ResponseEntity.ok(conversionService.findAllDistinctFromUnit(type));
    }

    @GetMapping("/toUnits")
    public ResponseEntity<?> getToUnits(@RequestParam ConversionCategories type) {
        return ResponseEntity.ok(conversionService.findAllDistinctToUnit(type));
    }
}