package backendApplication.model.dao;

import backendApplication.model.entities.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, Integer> {


    @Query("from City c order by size(c.tours) desc")
    List<City> findTop20MostPopularCities();

    City findByName(String name);
}

