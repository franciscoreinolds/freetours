package backendApplication.model.dao;

import backendApplication.model.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CityService {
    @Autowired
    CityRepository repo;

    public void save(City admin) {
        repo.save(admin);
    }

    public List<City> listAll() {
        return (List<City>) repo.findAll();
    }

    public City get(Integer id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}

