package backendApplication.model.dao;

import backendApplication.model.entities.Scheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class SchedulingService {
    @Autowired
    SchedulingRepository repo;

    public void save(Scheduling tour) {
        repo.save(tour);
    }

    public List<Scheduling> listAll() {
        return (List<Scheduling>) repo.findAll();
    }

    public Scheduling get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

