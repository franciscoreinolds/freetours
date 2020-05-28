package backendApplication.controller;

import backendApplication.dao.UserService;
import backendApplication.model.User;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

@RestController
public class AuthController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody User user) {

        try {
            userService.get(user.getUsername());

            return new ResponseEntity<HttpStatus> (HttpStatus.CONFLICT);
        } catch ( NoSuchElementException e) {
            try {
                String password_hash = Hashing.sha256()
                        .hashString(user.getPassword(), StandardCharsets.UTF_8)
                        .toString();
                user.setPassword(password_hash);
                userService.save(user);
            }catch (Exception ex) {
                // logger ex.printStackTrace();
                return new ResponseEntity<HttpStatus> (HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<HttpStatus> (HttpStatus.CREATED);
        }catch ( InvalidDataAccessApiUsageException e) {
            return new ResponseEntity<HttpStatus> (HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus>  login(@RequestBody User user) {

        try {
            User u = userService.get(user.getUsername());

            String password_hash = Hashing.sha256()
                    .hashString(user.getPassword(), StandardCharsets.UTF_8)
                    .toString();

            return u.getPassword().equals(password_hash)
                ? new ResponseEntity<HttpStatus> (HttpStatus.OK)
                : new ResponseEntity<HttpStatus> (HttpStatus.UNAUTHORIZED);
        }catch ( NoSuchElementException e) {
            return new ResponseEntity<HttpStatus> (HttpStatus.UNAUTHORIZED);
        }catch ( InvalidDataAccessApiUsageException e) {
            return new ResponseEntity<HttpStatus> (HttpStatus.BAD_REQUEST);
        }
    }
}
