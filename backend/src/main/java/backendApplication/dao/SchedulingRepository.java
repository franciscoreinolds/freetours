package backendApplication.dao;

import backendApplication.model.Scheduling;
import org.springframework.data.repository.CrudRepository;

public interface SchedulingRepository extends CrudRepository<Scheduling, String> {

}
