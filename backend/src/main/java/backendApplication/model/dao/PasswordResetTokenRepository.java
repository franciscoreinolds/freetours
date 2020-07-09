package backendApplication.model.dao;

import backendApplication.model.entities.PasswordResetToken;
import backendApplication.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    void deleteAllByUser(User user);
}
