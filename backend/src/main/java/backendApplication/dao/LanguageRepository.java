package backendApplication.dao;

import backendApplication.model.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, String> {

}

