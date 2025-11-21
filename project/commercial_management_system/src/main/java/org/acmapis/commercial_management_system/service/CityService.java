package org.acmapis.commercial_management_system.service;

import org.acmapis.commercial_management_system.entity.CityEntity;
import org.acmapis.commercial_management_system.model.dto.CityModel;
import org.acmapis.commercial_management_system.repository.CityRepository;
import org.acmapis.commercial_management_system.utils.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing city-related business logic.
 * Provides CRUD operations for city management.
 *
 * @author Commercial Management System
 * @version 1.0
 * @since 2025-11-20
 */
@Service
public class CityService {

    /**
     * Repository interface for accessing city data in the database.
     * Provides CRUD operations for city entities.
     */
    private final CityRepository cityRepository;

    /**
     * Mapper interface for converting between CityEntity and CityModel objects.
     * Handles automatic mapping using MapStruct framework.
     */
    private final CityMapper cityMapper;

    /**
     * Constructs a new CityService with the required dependencies.
     * Uses constructor-based dependency injection for better testability and immutability.
     *
     * @param cityRepository the repository for city data access operations
     * @param cityMapper the mapper for city entity-model conversions
     */
    @Autowired
    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Retrieves all cities from the database.
     *
     * @return List of CityModel objects representing all cities
     */
    public List<CityModel> getAllCities() {
        List<CityEntity> entities = cityRepository.findAll();
        return cityMapper.toModelList(entities);
    }

    /**
     * Retrieves a city by its unique identifier.
     *
     * @param cityId The unique identifier of the city
     * @return Optional containing the CityModel if found, empty otherwise
     */
    public Optional<CityModel> getCityById(Long cityId) {
        Optional<CityEntity> entity = cityRepository.findById(cityId);
        return entity.map(cityMapper::toModel);
    }

    /**
     * Creates a new city in the database.
     *
     * @param cityModel The CityModel containing the city data to save
     * @return The saved CityModel with generated ID
     */
    public CityModel saveCity(CityModel cityModel) {
        CityEntity entity = cityMapper.toEntity(cityModel);
        CityEntity savedEntity = cityRepository.save(entity);
        return cityMapper.toModel(savedEntity);
    }

    /**
     * Updates an existing city in the database.
     * Uses the find-modify-save pattern to ensure data integrity and prevent ID conflicts.
     *
     * @param cityId The unique identifier of the city to update
     * @param cityModel The CityModel containing the updated city data
     * @return The updated CityModel
     * @throws RuntimeException if the city with the given ID is not found
     */
    public CityModel updateCity(Long cityId, CityModel cityModel) {
        CityEntity existingEntity = cityRepository.findById(cityId)
            .orElseThrow(() -> new RuntimeException("City not found with ID: " + cityId));

        cityMapper.updateEntityFromModel(cityModel, existingEntity);

        CityEntity updatedEntity = cityRepository.save(existingEntity);
        return cityMapper.toModel(updatedEntity);
    }

    /**
     * Deletes a city by its unique identifier.
     *
     * @param cityId The unique identifier of the city to delete
     */
    public void deleteCityById(Long cityId) {
        cityRepository.deleteById(cityId);
    }
}
