package backendApplication.model.dao;

import backendApplication.model.entities.Continent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ContinentService {
    @Autowired
    ContinentRepository repo;

    public void save(Continent admin) {
        repo.save(admin);
    }

    public List<Continent> listAll() {
        return (List<Continent>) repo.findAll();
    }

    public Continent get(Integer id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

