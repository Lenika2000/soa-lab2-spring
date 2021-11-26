package ru.itmo.soalab2.repo;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.soalab2.model.City;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface  CityRepository extends PagingAndSortingRepository<City, Long>, JpaSpecificationExecutor<City> {
    List<City> findByNameContaining(String name);
    List<City> findByMetersAboveSeaLevelGreaterThan(int meters);
    @Query("SELECT DISTINCT c.metersAboveSeaLevel FROM City c ORDER BY c.metersAboveSeaLevel ASC")
    List<String> findDistinctMetersAboveSeaLevel();
    @Query("SELECT c.creationDate FROM City c where c.id = ?1 ")
    ZonedDateTime findCreationDateByCityId(long id);
}
