package ru.itmo.soalab2.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.soalab2.model.City;

import java.util.List;

@Repository
public interface  CityRepository extends CrudRepository<City, Long> {
    City findById(double id);
    List<City> findAll();
}
