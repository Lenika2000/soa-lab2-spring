package ru.itmo.soalab2.repo;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.soalab2.model.City;

import java.util.List;

@Repository
public interface  CityRepository extends PagingAndSortingRepository<City, Long>, JpaSpecificationExecutor<City> {
    City findById(double id);
    List<City> findByNameContaining(String name);
    List<City> findByMetersAboveSeaLevelGreaterThan(int meters);
    @Query("SELECT DISTINCT c.metersAboveSeaLevel FROM City c")
    List<String> findDistinctMetersAboveSeaLevel();
}
