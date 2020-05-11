package backendApplication.dao;

import backendApplication.model.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, String> {

}

