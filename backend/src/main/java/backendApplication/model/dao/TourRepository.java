package backendApplication.model.dao;

import backendApplication.model.entities.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {

}
