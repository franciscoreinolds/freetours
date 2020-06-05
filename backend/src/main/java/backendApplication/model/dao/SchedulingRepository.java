package backendApplication.model.dao;

import backendApplication.model.entities.Scheduling;
import org.springframework.data.repository.CrudRepository;

public interface SchedulingRepository extends CrudRepository<Scheduling, Integer> {

}
