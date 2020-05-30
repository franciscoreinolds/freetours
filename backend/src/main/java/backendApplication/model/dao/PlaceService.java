package backendApplication.model.dao;

import backendApplication.model.entities.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PlaceService {
    @Autowired
    PlaceRepository repo;

    public void save(Place admin) {
        repo.save(admin);
    }

    public List<Place> listAll() {
        return (List<Place>) repo.findAll();
    }

    public Place get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

