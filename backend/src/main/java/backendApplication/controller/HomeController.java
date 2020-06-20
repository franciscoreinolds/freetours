package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.NoSuchElementException;

@RestController
public class HomeController {

    @Autowired
    UserService userService;

   @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return "Hello " + username;
        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

   }
}
