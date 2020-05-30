package backendApplication.model.dao;

import backendApplication.model.entities.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, String> {

}

