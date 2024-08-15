package com.api.measureconverter.seed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.api.measureconverter.controller.UserController;
import com.api.measureconverter.model.ConverterEntity;
import com.api.measureconverter.repositories.ConverterRepository;
import com.api.measureconverter.repositories.UserRepository;
import com.api.measureconverter.utils.dto.RegisterDto;
import com.api.measureconverter.utils.dto.UserDto;
import com.api.measureconverter.utils.enums.ConversionCategories;
import com.api.measureconverter.utils.enums.Roles;
import com.api.measureconverter.utils.reponse.Response;

import jakarta.transaction.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    ConverterRepository conversionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Value("${ADMIN_USERNAME}")
    private String username;

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD}")
    private String adminPassword;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<ConverterEntity> conversions = List.of(
                // Length conversions
                createConversion("meter", "kilometer", 0.001, ConversionCategories.LENGTH),
                createConversion("kilometer", "meter", 1000, ConversionCategories.LENGTH),
                createConversion("meter", "centimeter", 100, ConversionCategories.LENGTH),
                createConversion("centimeter", "meter", 0.01, ConversionCategories.LENGTH),
                createConversion("inch", "foot", 0.0833333, ConversionCategories.LENGTH),
                createConversion("foot", "inch", 12, ConversionCategories.LENGTH),
                createConversion("yard", "meter", 0.9144, ConversionCategories.LENGTH),
                createConversion("meter", "yard", 1.09361, ConversionCategories.LENGTH),
                createConversion("mile", "kilometer", 1.60934, ConversionCategories.LENGTH),
                createConversion("kilometer", "mile", 0.621371, ConversionCategories.LENGTH),
                createConversion("millimeter", "meter", 0.001, ConversionCategories.LENGTH),
                createConversion("meter", "millimeter", 1000, ConversionCategories.LENGTH),
                createConversion("inch", "centimeter", 2.54, ConversionCategories.LENGTH),
                createConversion("centimeter", "inch", 0.393701, ConversionCategories.LENGTH),

                // Weight conversions
                createConversion("gram", "kilogram", 0.001, ConversionCategories.WEIGHT),
                createConversion("kilogram", "gram", 1000, ConversionCategories.WEIGHT),
                createConversion("pound", "kilogram", 0.453592, ConversionCategories.WEIGHT),
                createConversion("kilogram", "pound", 2.20462, ConversionCategories.WEIGHT),
                createConversion("ounce", "gram", 28.3495, ConversionCategories.WEIGHT),
                createConversion("gram", "ounce", 0.035274, ConversionCategories.WEIGHT),
                createConversion("ounce", "pound", 0.0625, ConversionCategories.WEIGHT),
                createConversion("pound", "ounce", 16, ConversionCategories.WEIGHT),
                createConversion("ton", "kilogram", 1000, ConversionCategories.WEIGHT),
                createConversion("kilogram", "ton", 0.001, ConversionCategories.WEIGHT),

                // Data size conversions
                createConversion("byte", "kilobyte", 0.001, ConversionCategories.DATA_SIZE),
                createConversion("kilobyte", "byte", 1000, ConversionCategories.DATA_SIZE),
                createConversion("megabyte", "kilobyte", 1000, ConversionCategories.DATA_SIZE),
                createConversion("kilobyte", "megabyte", 0.001, ConversionCategories.DATA_SIZE),
                createConversion("gigabyte", "megabyte", 1000, ConversionCategories.DATA_SIZE),
                createConversion("megabyte", "gigabyte", 0.001, ConversionCategories.DATA_SIZE),
                createConversion("terabyte", "gigabyte", 1000, ConversionCategories.DATA_SIZE),
                createConversion("gigabyte", "terabyte", 0.001, ConversionCategories.DATA_SIZE),
                createConversion("bit", "byte", 0.125, ConversionCategories.DATA_SIZE),
                createConversion("byte", "bit", 8, ConversionCategories.DATA_SIZE),
                createConversion("gigabit", "megabyte", 125, ConversionCategories.DATA_SIZE),
                createConversion("megabyte", "gigabit", 0.008, ConversionCategories.DATA_SIZE),

                // Area conversions
                createConversion("square_meter", "square_kilometer", 0.000001, ConversionCategories.AREA),
                createConversion("square_kilometer", "square_meter", 1000000, ConversionCategories.AREA),
                createConversion("acre", "square_meter", 4046.86, ConversionCategories.AREA),
                createConversion("square_meter", "acre", 0.000247105, ConversionCategories.AREA),
                createConversion("square_foot", "square_meter", 0.092903, ConversionCategories.AREA),
                createConversion("square_meter", "square_foot", 10.7639, ConversionCategories.AREA),
                createConversion("hectare", "square_meter", 10000, ConversionCategories.AREA),
                createConversion("square_meter", "hectare", 0.0001, ConversionCategories.AREA),
                createConversion("square_foot", "square_yard", 0.111111, ConversionCategories.AREA),
                createConversion("square_yard", "square_foot", 9, ConversionCategories.AREA),

                // Volume conversions
                createConversion("liter", "milliliter", 1000, ConversionCategories.VOLUME),
                createConversion("milliliter", "liter", 0.001, ConversionCategories.VOLUME),
                createConversion("gallon", "liter", 3.78541, ConversionCategories.VOLUME),
                createConversion("liter", "gallon", 0.264172, ConversionCategories.VOLUME),
                createConversion("cubic_meter", "liter", 1000, ConversionCategories.VOLUME),
                createConversion("liter", "cubic_meter", 0.001, ConversionCategories.VOLUME),
                createConversion("cup", "liter", 0.236588, ConversionCategories.VOLUME),
                createConversion("liter", "cup", 4.22675, ConversionCategories.VOLUME),
                createConversion("cubic_foot", "cubic_meter", 0.0283168, ConversionCategories.VOLUME),
                createConversion("cubic_meter", "cubic_foot", 35.3147, ConversionCategories.VOLUME),

                // Temperature conversions (handled in logic due to offsets)
                createConversion("celsius", "fahrenheit", 1.8, ConversionCategories.TEMPERATURE),
                createConversion("fahrenheit", "celsius", 0.5556, ConversionCategories.TEMPERATURE),
                createConversion("celsius", "kelvin", 1, ConversionCategories.TEMPERATURE), // Use offset in the logic
                createConversion("kelvin", "celsius", 1, ConversionCategories.TEMPERATURE), // Use offset in the logic

                // Time conversions
                createConversion("second", "minute", 0.0166667, ConversionCategories.TIME),
                createConversion("minute", "second", 60, ConversionCategories.TIME),
                createConversion("hour", "minute", 60, ConversionCategories.TIME),
                createConversion("minute", "hour", 0.0166667, ConversionCategories.TIME),
                createConversion("day", "hour", 24, ConversionCategories.TIME),
                createConversion("hour", "day", 0.0416667, ConversionCategories.TIME),
                createConversion("week", "day", 7, ConversionCategories.TIME),
                createConversion("day", "week", 0.142857, ConversionCategories.TIME),
                createConversion("millisecond", "second", 0.001, ConversionCategories.TIME),
                createConversion("second", "millisecond", 1000, ConversionCategories.TIME),
                createConversion("month", "day", 30.44, ConversionCategories.TIME), // Approximate
                createConversion("year", "day", 365.25, ConversionCategories.TIME), // Approximate

                // Speed conversions
                createConversion("meter_per_second", "kilometer_per_hour", 3.6, ConversionCategories.SPEED),
                createConversion("kilometer_per_hour", "meter_per_second", 0.277778, ConversionCategories.SPEED),
                createConversion("mile_per_hour", "kilometer_per_hour", 1.60934, ConversionCategories.SPEED),
                createConversion("kilometer_per_hour", "mile_per_hour", 0.621371, ConversionCategories.SPEED),
                createConversion("knot", "mile_per_hour", 1.15078, ConversionCategories.SPEED),
                createConversion("mile_per_hour", "knot", 0.868976, ConversionCategories.SPEED),
                createConversion("foot_per_second", "meter_per_second", 0.3048, ConversionCategories.SPEED),
                createConversion("meter_per_second", "foot_per_second", 3.28084, ConversionCategories.SPEED),
                createConversion("mach", "kilometer_per_hour", 1225.08, ConversionCategories.SPEED), // Approximate at
                                                                                                     // sea level
                createConversion("kilometer_per_hour", "mach", 0.000816273, ConversionCategories.SPEED), // Approximate
                                                                                                         // at sea level

                // Pressure conversions
                createConversion("pascal", "bar", 0.00001, ConversionCategories.PRESSURE),
                createConversion("bar", "pascal", 100000, ConversionCategories.PRESSURE),
                createConversion("psi", "bar", 0.0689476, ConversionCategories.PRESSURE),
                createConversion("bar", "psi", 14.5038, ConversionCategories.PRESSURE),
                createConversion("atmosphere", "pascal", 101325, ConversionCategories.PRESSURE),
                createConversion("pascal", "atmosphere", 0.00000986923, ConversionCategories.PRESSURE),
                createConversion("millimeter_of_mercury", "pascal", 133.322, ConversionCategories.PRESSURE),
                createConversion("pascal", "millimeter_of_mercury", 0.00750062, ConversionCategories.PRESSURE),
                createConversion("torr", "pascal", 133.322, ConversionCategories.PRESSURE),
                createConversion("pascal", "torr", 0.00750062, ConversionCategories.PRESSURE),

                // Energy conversions
                createConversion("joule", "kilojoule", 0.001, ConversionCategories.ENERGY),
                createConversion("kilojoule", "joule", 1000, ConversionCategories.ENERGY),
                createConversion("calorie", "joule", 4.184, ConversionCategories.ENERGY),
                createConversion("joule", "calorie", 0.239006, ConversionCategories.ENERGY),
                createConversion("watt_hour", "joule", 3600, ConversionCategories.ENERGY),
                createConversion("joule", "watt_hour", 0.000277778, ConversionCategories.ENERGY),
                createConversion("electronvolt", "joule", 1.60218e-19, ConversionCategories.ENERGY),
                createConversion("joule", "electronvolt", 6.24151e+18, ConversionCategories.ENERGY),
                createConversion("british_thermal_unit", "joule", 1055.06, ConversionCategories.ENERGY),
                createConversion("joule", "british_thermal_unit", 0.000947817, ConversionCategories.ENERGY),

                // Power conversions
                createConversion("watt", "kilowatt", 0.001, ConversionCategories.POWER),
                createConversion("kilowatt", "watt", 1000, ConversionCategories.POWER),
                createConversion("horsepower", "watt", 745.7, ConversionCategories.POWER),
                createConversion("watt", "horsepower", 0.00134102, ConversionCategories.POWER),
                createConversion("megawatt", "kilowatt", 1000, ConversionCategories.POWER),
                createConversion("kilowatt", "megawatt", 0.001, ConversionCategories.POWER),
                createConversion("kilovolt_ampere", "watt", 1000, ConversionCategories.POWER),
                createConversion("watt", "kilovolt_ampere", 0.001, ConversionCategories.POWER),
                createConversion("erg_per_second", "watt", 1e-7, ConversionCategories.POWER),
                createConversion("watt", "erg_per_second", 1e+7, ConversionCategories.POWER),

                // Force conversions
                createConversion("newton", "kilonewton", 0.001, ConversionCategories.FORCE),
                createConversion("kilonewton", "newton", 1000, ConversionCategories.FORCE),
                createConversion("pound_force", "newton", 4.44822, ConversionCategories.FORCE),
                createConversion("newton", "pound_force", 0.224809, ConversionCategories.FORCE),
                createConversion("dyne", "newton", 0.00001, ConversionCategories.FORCE),
                createConversion("newton", "dyne", 100000, ConversionCategories.FORCE),
                createConversion("gram_force", "newton", 0.00980665, ConversionCategories.FORCE),
                createConversion("newton", "gram_force", 101.971621, ConversionCategories.FORCE),
                createConversion("poundal", "newton", 0.138255, ConversionCategories.FORCE),
                createConversion("newton", "poundal", 7.23301, ConversionCategories.FORCE),

                // Angle conversions
                createConversion("degree", "radian", 0.0174533, ConversionCategories.ANGLE),
                createConversion("radian", "degree", 57.2958, ConversionCategories.ANGLE),
                createConversion("gradian", "degree", 0.9, ConversionCategories.ANGLE),
                createConversion("degree", "gradian", 1.11111, ConversionCategories.ANGLE),
                createConversion("arcminute", "degree", 0.0166667, ConversionCategories.ANGLE),
                createConversion("degree", "arcminute", 60, ConversionCategories.ANGLE),
                createConversion("arcsecond", "degree", 0.000277778, ConversionCategories.ANGLE),
                createConversion("degree", "arcsecond", 3600, ConversionCategories.ANGLE),

                // Frequency conversions
                createConversion("hertz", "kilohertz", 0.001, ConversionCategories.FREQUENCY),
                createConversion("kilohertz", "hertz", 1000, ConversionCategories.FREQUENCY),
                createConversion("megahertz", "kilohertz", 1000, ConversionCategories.FREQUENCY),
                createConversion("kilohertz", "megahertz", 0.001, ConversionCategories.FREQUENCY),
                createConversion("gigahertz", "megahertz", 1000, ConversionCategories.FREQUENCY),
                createConversion("megahertz", "gigahertz", 0.001, ConversionCategories.FREQUENCY),
                createConversion("revolutions_per_minute", "hertz", 0.0166667, ConversionCategories.FREQUENCY),
                createConversion("hertz", "revolutions_per_minute", 60, ConversionCategories.FREQUENCY),
                createConversion("terahertz", "gigahertz", 1000, ConversionCategories.FREQUENCY),
                createConversion("gigahertz", "terahertz", 0.001, ConversionCategories.FREQUENCY),

                // Electric current conversions
                createConversion("ampere", "milliampere", 1000, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("milliampere", "ampere", 0.001, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("ampere", "kiloampere", 0.001, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("kiloampere", "ampere", 1000, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("microampere", "ampere", 0.000001, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("ampere", "microampere", 1000000, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("kiloampere", "milliampere", 1e+6, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("milliampere", "kiloampere", 1e-6, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("abampere", "ampere", 10, ConversionCategories.ELECTRIC_CURRENT),
                createConversion("ampere", "abampere", 0.1, ConversionCategories.ELECTRIC_CURRENT));

        for (ConverterEntity conversion : conversions) {
            if (!conversionRepository.existsByFromUnitAndToUnit(conversion.getFromUnit(), conversion.getToUnit())) {
                conversionRepository.save(conversion);
            }
        }

        if (!isUserInfoNull(List.of(username, adminEmail, adminPassword))) {
            System.out.println("\n\nAdmin user not created, perhaps you didn't set the env variables for it?\n\n");
            return;
        }

        if (!userRepository.existsByEmailAndUsername(adminEmail, username)) {
            RegisterDto registerDto = new RegisterDto(username, adminEmail, adminPassword, adminPassword);
            createAdminUser(registerDto);
        } else {
            System.out.println("\n\nAdmin user already exists, Email or Username already begin used\n\n");
        }
    }

    private ResponseEntity<Response<UserDto>> createAdminUser(RegisterDto registerDto) {
        return userController.create(registerDto, Roles.ROLE_ADMIN);
    }

    private Boolean isUserInfoNull(List<String> userInfo) {
        for (String info : userInfo) {
            if (info == null || info.isEmpty()) {
                System.out.println("\n\nAdmin user info is missing, please provide the required information\n\n");
                return false;
            }
        }
        return true;
    }

    private ConverterEntity createConversion(String fromUnit, String toUnit, double factor, ConversionCategories type) {
        return ConverterEntity.builder()
                .fromUnit(fromUnit)
                .toUnit(toUnit)
                .factor(factor)
                .type(type)
                .build();
    }
}