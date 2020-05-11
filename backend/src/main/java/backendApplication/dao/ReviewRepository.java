package backendApplication.dao;

import backendApplication.model.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, String> {

}
