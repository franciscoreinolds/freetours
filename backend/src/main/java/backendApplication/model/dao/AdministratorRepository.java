package backendApplication.model.dao;

import backendApplication.model.entities.Administrator;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {

}
