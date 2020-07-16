package backendApplication.controller;

import backendApplication.model.dao.CityService;
import backendApplication.model.entities.City;
import backendApplication.model.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @RequestMapping(value = "/cities", method = RequestMethod.GET)
    public List<City> getCities() {
        return cityService.listAll()
                .stream()
                .map(c -> {
                    c = (City) c.clone();
                    c.setTours(null);
                    return c;
                })
                .collect(Collectors.toList());

    }
}
