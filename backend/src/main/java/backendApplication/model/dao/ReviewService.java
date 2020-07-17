package backendApplication.model.dao;

import backendApplication.model.entities.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ReviewService {
    @Autowired
    ReviewRepository repo;

    public void save(Review tour) {
        repo.save(tour);
    }

    public List<Review> listAll() {
        return (List<Review>) repo.findAll();
    }

    public Review get(Integer id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public Review findByToken(String token) {
        return repo.findByToken(token);
    }

}

