package com.api.measureconverter.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.measureconverter.model.ConverterEntity;
import com.api.measureconverter.utils.enums.ConversionCategories;

@Repository
public interface ConverterRepository extends JpaRepository<ConverterEntity, UUID> {
    Optional<ConverterEntity> findByFromUnitAndToUnitAndType(String fromUnit, String toUnit, ConversionCategories type);

    @Query("SELECT DISTINCT c.type FROM ConverterEntity c")
    List<ConversionCategories> findAllDistinctTypes();

    List<ConverterEntity> findByType(ConversionCategories type);

    boolean existsByFromUnitAndToUnit(String fromUnit, String toUnit);

    @Query("SELECT DISTINCT c.toUnit FROM ConverterEntity c WHERE c.type = :type")
    List<String> findAllDistinctToUnit(@Param("type") ConversionCategories type);

    @Query("SELECT DISTINCT c.fromUnit FROM ConverterEntity c WHERE c.type = :type")
    List<String> findAllDistinctFromUnit(@Param("type") ConversionCategories type);
}