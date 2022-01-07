package ru.itmo.soalab2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.itmo.soalab2.controller.CityRequestParams;
import ru.itmo.soalab2.model.*;
import ru.itmo.soalab2.repo.CityFilterSpecification;
import ru.itmo.soalab2.repo.CityRepository;
import ru.itmo.soalab2.validators.CityValidator;
import ru.itmo.soalab2.validators.ValidateFieldsException;

import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class SoapCityService {
    private final CityValidator cityValidator;
    private final CityRepository cityRepository;

    @Autowired
    public SoapCityService(CityRepository cityRepository) {
        this.cityValidator = new CityValidator();
        this.cityRepository = cityRepository;
    }

    public City createCity(CityFromClient newCity) throws Exception {
        City validCity = cityValidator.validate(newCity);
        validCity.setCreationDate(ZonedDateTime.now());
        Long id = cityRepository.save(validCity).getId();
        return cityRepository.findById(id).get();
    }

    public City updateCity(CityFromClient updatedCity) throws Exception {
        City validCity = cityValidator.validate(updatedCity);
        boolean isFound = cityRepository.existsById(updatedCity.getId());
        if (isFound) {
            validCity.setCreationDate(cityRepository.findCreationDateByCityId(updatedCity.getId()));
            cityRepository.save(validCity);
            return validCity;
        }
        return null;
    }

    public void deleteCity(Long id) throws CityServiceException {
        if (Objects.isNull(id)) {
            throw new CityServiceException("id is incorrect", 400);
        }
        boolean isFound = cityRepository.existsById(id);
        if (!isFound) {
            throw new CityServiceException("city with id " + id + " is not found", 404);
        }
        cityRepository.deleteById(id);
    }

    public PaginationResult getAllCities(CityRequestParams filterParams) {
        CityFilterSpecification spec = new CityFilterSpecification(filterParams);
        try {
            Sort currentSorting = filterParams.parseSorting();
            Pageable sortedBy = PageRequest.of(filterParams.page, filterParams.size, currentSorting);
            Page<City> res = cityRepository.findAll(spec, sortedBy);
            long count = cityRepository.count(spec);
            PaginationResult result = new PaginationResult(filterParams.size, filterParams.page, count,res.getContent().toArray(new City[res.getContent().size()]));
            return result;
        } catch (ParseException e) {
            return null;
        }
    }

    public ArrayList<City> filterCitiesByMetersAboveSeaLevel(int metersAboveSeaLevel) {
        List<City> cities = cityRepository.findByMetersAboveSeaLevelGreaterThan(metersAboveSeaLevel);
        return new ArrayList<>(cities);
    }

    private ResponseEntity<?> sendErrorList(ValidateFieldsException ex) {
        return ResponseEntity.status(400).body(ex.getErrorMsg());
    }

    public ArrayList<City> getCitiesByName(String name) {
        List<City> cities = cityRepository.findByNameContaining(name);
        return new ArrayList<>(cities);
    }

    public ArrayList<String> getUniqueMetersAboveSeeLevel() {
        List<String> meters = cityRepository.findDistinctMetersAboveSeaLevel();
        return new ArrayList<>(meters);
    }
}
