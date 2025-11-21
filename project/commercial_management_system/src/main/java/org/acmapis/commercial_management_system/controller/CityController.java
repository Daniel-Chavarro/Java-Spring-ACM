package org.acmapis.commercial_management_system.controller;

import org.acmapis.commercial_management_system.model.dto.CityModel;
import org.acmapis.commercial_management_system.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for City management operations.
 * Provides endpoints for CRUD operations on cities.
 *
 * @author Commercial Management System Team
 * @version 1.0
 * @since 2025-11-21
 */
@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin(origins = "*")
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Get all cities in the system.
     *
     * @return List of all cities
     */
    @GetMapping
    public ResponseEntity<List<CityModel>> getAllCities() {
        List<CityModel> cities = cityService.getAllCities();
        return ResponseEntity.ok(cities);
    }

    /**
     * Get a specific city by ID.
     *
     * @param id The city ID
     * @return City if found, 404 if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<CityModel> getCityById(@PathVariable Long id) {
        Optional<CityModel> city = cityService.getCityById(id);
        return city.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new city.
     *
     * @param city City data to create
     * @return Created city with 201 status
     */
    @PostMapping
    public ResponseEntity<CityModel> createCity(@RequestBody CityModel city) {
        CityModel savedCity = cityService.saveCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }

    /**
     * Update an existing city.
     *
     * @param id The city ID to update
     * @param city Updated city data
     * @return Updated city
     */
    @PutMapping("/{id}")
    public ResponseEntity<CityModel> updateCity(@PathVariable Long id, @RequestBody CityModel city) {
        city.setCityId(id);
        CityModel updatedCity = cityService.updateCity(id,city);
        return ResponseEntity.ok(updatedCity);
    }

    /**
     * Delete a city by ID.
     *
     * @param id The city ID to delete
     * @return 204 No Content if successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCityById(id);
        return ResponseEntity.noContent().build();
    }
}