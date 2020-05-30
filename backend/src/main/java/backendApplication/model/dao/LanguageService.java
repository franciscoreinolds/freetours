package backendApplication.model.dao;

import backendApplication.model.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class LanguageService {
    @Autowired
    LanguageRepository repo;

    public void save(Language admin) {
        repo.save(admin);
    }

    public List<Language> listAll() {
        return (List<Language>) repo.findAll();
    }

    public Language get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

