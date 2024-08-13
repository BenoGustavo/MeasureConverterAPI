package com.api.measureconverter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.measureconverter.model.ConverterEntity;
import com.api.measureconverter.services.ConverterService;
import com.api.measureconverter.utils.dto.ConverterDto;
import com.api.measureconverter.utils.enums.ConversionCategories;
import com.api.measureconverter.utils.reponse.Response;

@RestController
@RequestMapping("/api/convert")
public class ConverterController {

    @Autowired
    private final ConverterService conversionService;

    public ConverterController(ConverterService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping
    public ResponseEntity<Response<ConverterDto>> convert(
            @RequestParam double value,
            @RequestParam String fromUnit,
            @RequestParam String toUnit,
            @RequestParam ConversionCategories type) {
        ConverterDto result = conversionService.convert(value, fromUnit, toUnit, type);

        Response<ConverterDto> response = new Response.Builder<ConverterDto>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/types")
    public ResponseEntity<Response<List<ConversionCategories>>> getTypes() {
        List<ConversionCategories> result = conversionService.findAllDistinctTypes();

        Response<List<ConversionCategories>> response = new Response.Builder<List<ConversionCategories>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/byType")
    public ResponseEntity<Response<List<ConverterEntity>>> getByType(@RequestParam ConversionCategories type) {
        List<ConverterEntity> result = conversionService.findByType(type);
        Response<List<ConverterEntity>> response = new Response.Builder<List<ConverterEntity>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/fromUnits")
    public ResponseEntity<Response<List<String>>> getFromUnits(@RequestParam ConversionCategories type) {
        List<String> result = conversionService.findAllDistinctFromUnit(type);

        Response<List<String>> response = new Response.Builder<List<String>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/toUnits")
    public ResponseEntity<Response<List<String>>> getToUnits(@RequestParam ConversionCategories type) {
        List<String> result = conversionService.findAllDistinctToUnit(type);

        Response<List<String>> response = new Response.Builder<List<String>>()
                .result("Success")
                .status(200)
                .data(result)
                .build();

        return ResponseEntity.ok(response);
    }
}