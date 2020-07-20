package backendApplication.model.dao;


import backendApplication.model.entities.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourRepository extends CrudRepository<Tour, Integer> {

    @Query("from Tour t where (t.city.name = :cityName) and (not size(t.active) = 0)")
    List<Tour> findRandomActiveTours(@Param("cityName") String cityName);

}
