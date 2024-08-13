package com.api.measureconverter.utils.dto;

import com.api.measureconverter.utils.enums.ConversionCategories;

import lombok.Data;

@Data
public class ConverterDto {
    private String fromUnit;
    private String toUnit;
    private double factor;
    private ConversionCategories type;

    private String result;

    private ConverterDto(String fromUnit, String toUnit, double factor, ConversionCategories type, String result) {
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.factor = factor;
        this.type = type;
        this.result = result;
    }

    public static class Builder {
        private String fromUnit;
        private String toUnit;
        private double factor;
        private ConversionCategories type;
        private String result;

        public Builder fromUnit(String fromUnit) {
            this.fromUnit = fromUnit;
            return this;
        }

        public Builder toUnit(String toUnit) {
            this.toUnit = toUnit;
            return this;
        }

        public Builder factor(double factor) {
            this.factor = factor;
            return this;
        }

        public Builder type(ConversionCategories type) {
            this.type = type;
            return this;
        }

        public Builder result(String result) {
            this.result = result;
            return this;
        }

        public ConverterDto build() {
            return new ConverterDto(fromUnit, toUnit, factor, type, result);
        }
    }
}
