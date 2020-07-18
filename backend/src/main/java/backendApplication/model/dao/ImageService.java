package backendApplication.model.dao;

import backendApplication.model.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ImageService {

    @Autowired
    ImageRepository repo;

    public void save(Image image) {
        repo.save(image);
    }

    public List<Image> listAll() {
        return (List<Image>) repo.findAll();
    }

    public Image get(int id) throws NoSuchElementException {
        return repo.findById(id).get();
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

}
