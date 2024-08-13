package com.api.measureconverter.model;

import java.util.UUID;

import com.api.measureconverter.utils.enums.ConversionCategories;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(name = "unit_conversion", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "from_unit", "to_unit" })
})
public class ConverterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "from_unit")
    private String fromUnit;

    @Column(name = "to_unit")
    private String toUnit;

    private double factor;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ConversionCategories type;

    // Builder class
    public static class Builder {
        private UUID id;
        private String fromUnit;
        private String toUnit;
        private double factor;
        private ConversionCategories type;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

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

        public ConverterEntity build() {
            ConverterEntity entity = new ConverterEntity();
            entity.id = this.id;
            entity.fromUnit = this.fromUnit;
            entity.toUnit = this.toUnit;
            entity.factor = this.factor;
            entity.type = this.type;
            return entity;
        }
    }

    // Static method to get a new Builder instance
    public static Builder builder() {
        return new Builder();
    }
}