package backendApplication.dao;

import backendApplication.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class TourService {
    @Autowired
    TourRepository repo;

    public void save(Tour tour) {
        repo.save(tour);
    }

    public List<Tour> listAll() {
        return (List<Tour>) repo.findAll();
    }

    public Tour get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

