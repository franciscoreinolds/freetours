package backendApplication.dao;

import backendApplication.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {

}

