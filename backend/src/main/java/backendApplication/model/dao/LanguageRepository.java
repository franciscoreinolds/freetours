package backendApplication.model.dao;

import backendApplication.model.entities.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Integer> {

}

