package backendApplication.model.dao;

import backendApplication.model.entities.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

    City findByName(String name);
}

