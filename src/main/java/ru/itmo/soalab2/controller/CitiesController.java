package ru.itmo.soalab2.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmo.soalab2.model.City;
import ru.itmo.soalab2.repo.CityRepository;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CityRepository cityRepository;

    CitiesController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @PostMapping
    City addCity(@RequestBody City newCity) {
        newCity.setCreationDate(ZonedDateTime.now());
        return cityRepository.save(newCity);
    }

    @GetMapping
    List<City> getAllCities() {
        List<City> allCities = cityRepository.findAll();
        return allCities;
    }

    @DeleteMapping(value = "/{id}")
    void deleteCity(@PathVariable Long id) {
        cityRepository.deleteById(id);
    }
}
