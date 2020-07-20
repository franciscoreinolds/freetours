package backendApplication.model.dao;

import backendApplication.model.entities.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public Tour get(Integer id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Tour> getRandomActiveTours(String cityName) {
        return repo.findRandomActiveTours(cityName);
    }

}

