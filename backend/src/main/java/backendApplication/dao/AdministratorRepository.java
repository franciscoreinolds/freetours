package backendApplication.dao;

import backendApplication.model.Administrator;
import org.springframework.data.repository.CrudRepository;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {

}
