package backendApplication.controller;

import backendApplication.MyUserDetailsService;
import backendApplication.utils.JwtUtil;
import backendApplication.model.dao.UserService;
import backendApplication.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> registerUser(@RequestBody User user) {

        try {
            userService.get(user.getUsername());

            return new ResponseEntity<HttpStatus> (HttpStatus.CONFLICT);
        } catch ( NoSuchElementException e) {
            try {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                userService.save(user);
            }catch (Exception ex) {
                // logger ex.printStackTrace();
                return new ResponseEntity<> (HttpStatus.NOT_ACCEPTABLE);
            }

            return new ResponseEntity<> (HttpStatus.CREATED);
        }catch ( InvalidDataAccessApiUsageException e) {
            return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        }catch (BadCredentialsException e) {
            new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }
}
