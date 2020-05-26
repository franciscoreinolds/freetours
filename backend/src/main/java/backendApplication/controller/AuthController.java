package backendApplication.controller;

import backendApplication.dao.UserService;
import backendApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity registeUser(@RequestBody User user) {

        try {
            userService.get(user.getUsername());

            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch ( NoSuchElementException e) {
            try {
                userService.save(user);
            }catch (Exception ex) {
                // logger ex.printStackTrace();
                return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity(HttpStatus.OK);
        }catch ( InvalidDataAccessApiUsageException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user) {

        try {
            User u = userService.get(user.getUsername());
            
            return u.getPassword().equals(user.getPassword())
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }catch ( NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }catch ( InvalidDataAccessApiUsageException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
