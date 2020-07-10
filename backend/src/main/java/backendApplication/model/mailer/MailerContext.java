package backendApplication.model.mailer;

import backendApplication.model.emailBuilder.Email;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class MailerContext {

    private MailerStrategy mailerStrategy = null;

    public void setStrategy(MailerStrategy mailerStrategy) {
        this.mailerStrategy = mailerStrategy;
    }

    public void send(Email email) {

        if(this.mailerStrategy == null) {
            System.out.println("Entrei");
            this.mailerStrategy = new SendGridWebAPI();
        }

        this.mailerStrategy.send(email);
    }

}
