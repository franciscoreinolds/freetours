package backendApplication.controller;

import backendApplication.model.dao.CityService;
import backendApplication.model.entities.City;
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
                    City city = new City();
                    city.setId(c.getId());
                    city.setName(c.getName());
                    return city;
                })
                .collect(Collectors.toList());

    }
}
