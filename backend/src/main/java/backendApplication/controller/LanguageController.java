package backendApplication.controller;

import backendApplication.model.dao.LanguageService;
import backendApplication.model.entities.Language;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LanguageController {
    @Autowired
    LanguageService languageService;

    @RequestMapping(value = "/languages", method = RequestMethod.GET)
    public List<Language> getLanguages() {
        return languageService.listAll()
                .stream()
                .map(l -> {
                    l = (Language) l.clone();
                    l.setTours(null);
                    return l;
                })
                .collect(Collectors.toList());

    }
}
