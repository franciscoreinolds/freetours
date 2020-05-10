package backendApplication.model;

import backendApplication.model.UserRepository;
import backendApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }

    public List<User> listAll() {
        return (List<User>) repo.findAll();
    }

    public User get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }


    public void delete(String id) {
        repo.deleteById(id);
    }
}

