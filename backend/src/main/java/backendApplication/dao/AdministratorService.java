package backendApplication.dao;

import backendApplication.model.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AdministratorService {
    @Autowired
    AdministratorRepository repo;

    public void save(Administrator admin) {
        repo.save(admin);
    }

    public List<Administrator> listAll() {
        return (List<Administrator>) repo.findAll();
    }

    public Administrator get(String id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}

