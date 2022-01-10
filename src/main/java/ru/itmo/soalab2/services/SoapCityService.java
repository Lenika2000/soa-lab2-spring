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
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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

    public City createCity(CityFromClient newCity) throws ValidateFieldsException {
        City validCity = null;
        try {
            validCity = cityValidator.validate(newCity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        validCity.setCreationDate(LocalDateTime.now());
        Long id = cityRepository.save(validCity).getId();
        return cityRepository.findById(id).get();
    }

    public City updateCity(CityFromClient updatedCity) throws ValidateFieldsException {
        City validCity = null;
        try {
            validCity = cityValidator.validate(updatedCity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        boolean isFound = cityRepository.existsById(updatedCity.getId());
        if (isFound) {
            validCity.setCreationDate(cityRepository.findCreationDateByCityId(updatedCity.getId()));
            cityRepository.save(validCity);
            return validCity;
        }
        return null;
    }

    public void deleteCity(Long id) throws WrongParamsFault {
        if (Objects.isNull(id)) {
            throw new WrongParamsFault("Id is incorrect", new WebServiceExceptionFaultDto("Id is incorrect", 400));
        }
        boolean isFound = cityRepository.existsById(id);
        if (!isFound) {
            String errorMsg = "City with id " + id + " is not found";
            throw new WrongParamsFault(errorMsg, new WebServiceExceptionFaultDto(errorMsg, 404));
        }
        cityRepository.deleteById(id);
    }

    public PaginationResult getAllCities(CityRequestParams filterParams) throws WrongParamsFault {
        CityFilterSpecification spec = new CityFilterSpecification(filterParams);
        try {
            Sort currentSorting = filterParams.parseSorting();
            Pageable sortedBy = PageRequest.of(filterParams.page, filterParams.size, currentSorting);
            Page<City> res = cityRepository.findAll(spec, sortedBy);
            long count = cityRepository.count(spec);
            PaginationResult result = new PaginationResult(filterParams.size, filterParams.page, count,res.getContent().toArray(new City[res.getContent().size()]));
            return result;
        } catch (ParseException e) {
            throw new WrongParamsFault(e.getMessage(), new WebServiceExceptionFaultDto(e.getMessage(), 404));
        }
    }

    public ArrayList<City> filterCitiesByMetersAboveSeaLevel(int metersAboveSeaLevel) throws WrongParamsFault {
        List<City> cities = cityRepository.findByMetersAboveSeaLevelGreaterThan(metersAboveSeaLevel);
        if (cities.isEmpty()) {
            String errorMsg = "Cities with meters above sea level more than " + metersAboveSeaLevel + " are not found";
            throw new WrongParamsFault(errorMsg, new WebServiceExceptionFaultDto(errorMsg, 404));
        } else {
            return new ArrayList<>(cities);
        }
    }

    private ResponseEntity<?> sendErrorList(ValidateFieldsException ex) {
        return ResponseEntity.status(400).body(ex.getErrorListWrap());
    }

    public ArrayList<City> getCitiesByName(String name) throws WrongParamsFault {
        List<City> cities = cityRepository.findByNameContaining(name);
        if (cities.isEmpty()) {
            String errorMsg = "Cities with name " + name + " are not found";
            throw new WrongParamsFault(errorMsg, new WebServiceExceptionFaultDto(errorMsg, 404));
        } else {
            return new ArrayList<>(cities);
        }
    }

    public ArrayList<String> getUniqueMetersAboveSeaLevel() throws WrongParamsFault {
        List<String> meters = cityRepository.findDistinctMetersAboveSeaLevel();
        if (meters.isEmpty()) {
            String errorMsg = "Unique values of meters above sea level are not found";
            throw new WrongParamsFault(errorMsg, new WebServiceExceptionFaultDto(errorMsg, 404));
        } else {
            return new ArrayList<>(meters);
        }
    }
}
