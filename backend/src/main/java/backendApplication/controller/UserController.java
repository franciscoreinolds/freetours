package backendApplication.controller;

import backendApplication.model.dao.UserService;
import backendApplication.model.entities.User;
import backendApplication.viewmodel.ProfileView;
import backendApplication.viewmodel.ProfileViewAll;
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
    public ResponseEntity<ProfileView> profile(@PathVariable String username) {

        User u = userService.getByUsername(username);

        if(u == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String jwt_username = SecurityContextHolder.getContext().getAuthentication().getName();

        return jwt_username.equals(username)
                ? ResponseEntity.ok(new ProfileViewAll(u))
                : ResponseEntity.ok(new ProfileView(u));
    }

}
