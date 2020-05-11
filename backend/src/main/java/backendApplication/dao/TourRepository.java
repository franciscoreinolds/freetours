package backendApplication.dao;

import backendApplication.model.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, String> {

}
