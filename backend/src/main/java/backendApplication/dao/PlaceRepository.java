package backendApplication.dao;

import backendApplication.model.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, String> {

}

