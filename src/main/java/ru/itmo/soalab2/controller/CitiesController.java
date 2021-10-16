package ru.itmo.soalab2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.soalab2.model.City;
import ru.itmo.soalab2.repo.CityRepository;
import ru.itmo.soalab2.services.CityService;
import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {

    private final CityRepository cityRepository;
    private CityService cityService;

    CitiesController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
        this.cityService = new CityService(cityRepository);
    }

    @GetMapping
    List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    City getCityById(@PathVariable Long id) {
        City city = cityRepository.findById(id).get();
        return city;
    }

    @PostMapping
    ResponseEntity<?> addCity(@RequestBody City newCity) throws Exception {
        return cityService.createCity(newCity);
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateCity(@RequestBody City updatedCity) throws Exception {
        return cityService.updateCity(updatedCity);
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id);
    }
}
