package backendApplication.controller;

import backendApplication.model.ImageStoreService;
import backendApplication.model.Pair;
import backendApplication.model.dao.CityService;
import backendApplication.model.dao.ImageService;
import backendApplication.model.entities.City;
import backendApplication.model.entities.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @Autowired
    ImageService imageService;

    @Autowired
    private ImageStoreService imageStoreService;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<City> getCities() {
        return cityService.listAll()
                .stream()
                .map(c -> {
                    City city = new City();
                    city.setId(c.getId());
                    city.setName(c.getName());
                    return city;
                })
                .collect(Collectors.toList());

    }

    @RequestMapping(value = "/city/{cityName}", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addImageToCity(@PathVariable String cityName, @RequestPart MultipartFile cityImage) {

        City city = cityService.getByName(cityName);
        if(city == null)
            return new ResponseEntity<>("City does not exist", HttpStatus.NOT_FOUND);

        if(city.getImage() != null)
            return new ResponseEntity<>("City already has an image", HttpStatus.CONFLICT);

        if(!cityImage.isEmpty()) {

            List<String> filenames = imageStoreService.storeImage(List.of(new Pair<>(cityImage, cityName + ".png")));

            Image img = new Image();
            img.setImage(filenames.get(0));
            imageService.save(img);

            city.setImage(img);
            cityService.save(city);

            return new ResponseEntity<>("Image added", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something with the image is not right", HttpStatus.BAD_REQUEST);

    }

}
