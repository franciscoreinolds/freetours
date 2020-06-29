package backendApplication.controller;

import backendApplication.MyUserDetailsService;
import backendApplication.model.dao.PasswordResetTokenService;
import backendApplication.utils.JwtUtil;
import backendApplication.model.dao.UserService;
import backendApplication.model.entities.User;
import backendApplication.viewmodel.PasswordChange;
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

import javax.validation.Valid;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

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
        } catch(BadCredentialsException e) {
            return new ResponseEntity<> (HttpStatus.UNAUTHORIZED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(jwt);
    }


    @RequestMapping(value = "/reset_password", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@RequestBody User userBody) {

        if(userBody.getEmail() == null)
            return new ResponseEntity<>("An email must be sent as a parameter", HttpStatus.BAD_REQUEST);

        User user = userService.findUserByEmail(userBody.getEmail());

        if (user == null)
            return new ResponseEntity<>("A valid email must be sent as a parameter", HttpStatus.BAD_REQUEST);

        String token = UUID.randomUUID().toString();
        passwordResetTokenService.createPasswordResetTokenForUser(user, token);

        // send mail to user
        // ..
        // ..

        return new ResponseEntity<>("A reset password mail was sent to your email", HttpStatus.CREATED);

    }


    @RequestMapping(value = "/change_password", method = RequestMethod.POST)
    public ResponseEntity<String> changePassword(@RequestParam("token") String token, @RequestBody PasswordChange passwordChange) {

        String result = passwordResetTokenService.validatePasswordResetToken(token);

        if(result == null){

            User user = passwordResetTokenService.getUserByPasswordResetToken(token);

            if(passwordChange.getPassword().equals(passwordChange.getPassword_confirmation())) {

                user.setPassword(passwordEncoder.encode(passwordChange.getPassword()));
                userService.save(user);

                passwordResetTokenService.deleteAllByUser(token);

                return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);

            } else {

                return new ResponseEntity<>("Password and Password confirmation are different", HttpStatus.BAD_REQUEST);

            }

        }
        else if (result.equals("Expired"))
            return new ResponseEntity<>("Token expired. Please request a new one", HttpStatus.GONE);

        else
            return new ResponseEntity<>("Token does not exist", HttpStatus.NOT_FOUND);

    }

}
