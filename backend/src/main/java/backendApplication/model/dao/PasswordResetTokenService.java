package backendApplication.model.dao;

import backendApplication.model.entities.PasswordResetToken;
import backendApplication.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;

@Service
@Transactional
public class PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository repo;

    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        repo.save(myToken);
    }

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = repo.findByToken(token);

        return !isTokenFound(passToken) ? "Invalid Token"
                : isTokenExpired(passToken) ? "Expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public User getUserByPasswordResetToken(String token) {
        return repo.findByToken(token).getUser();
    }

    public void deleteAllByUser(String token) {
        repo.deleteAllByUser(getUserByPasswordResetToken(token));
    }
}
