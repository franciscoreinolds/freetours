package backendApplication.model.dao;

import backendApplication.model.entities.Continent;
import org.springframework.data.repository.CrudRepository;

public interface ContinentRepository extends CrudRepository<Continent, Integer> {

}

