package backendApplication.controller;

import backendApplication.controller.expeptions.NotFoundException;
import backendApplication.model.dao.UserService;
import backendApplication.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> profile(@PathVariable String username) {

        try {

            String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

            if(jwt_username.equals(username)){

                User u = userService.getByUsername(username);
                return ResponseEntity.ok(u);

            } else {

                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

            }

        }catch (NoSuchElementException e) {
            throw new NotFoundException();
        }

    }

}
