package backendApplication.model.dao;

import backendApplication.model.entities.City;
import backendApplication.model.entities.Tour;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Integer> {

    @Query("from Tour t where not size(t.active) = 0")
    List<Tour> findRandomActiveTours(Pageable pageable);
}
