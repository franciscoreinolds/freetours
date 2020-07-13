package backendApplication.model.mailer;

import backendApplication.model.emailBuilder.Email;

public interface MailerStrategy {
    void send(Email email);
    MailerStrategyName getStrategyName();
}
