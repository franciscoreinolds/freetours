package backendApplication.model.mailer;

import backendApplication.model.emailBuilder.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope("singleton")
public class MailerContext {

    @Value("${app.sendgrid.key}")
    private String appKey;

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private Integer port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    private Map<MailerStrategyName,MailerStrategy> strategies;
    private MailerStrategy currentStrategy;

    public MailerContext() {
        this.strategies = new HashMap<>();
        this.currentStrategy = null;
    }

    public void setStrategy(MailerStrategy mailerStrategy) {

        MailerStrategy ms = this.strategies.get(mailerStrategy.getStrategyName());

        if(ms == null) {
            this.strategies.put(mailerStrategy.getStrategyName(), mailerStrategy);
            ms = mailerStrategy;
        }

        this.currentStrategy = ms;

    }

    public void send(Email email) {

        if(this.strategies.size() == 0){

            MailerStrategy sgwa = new SendGridWebAPI(this.appKey);
            this.strategies.put(sgwa.getStrategyName(), sgwa);

            MailerStrategy sgsmtp = new SendGridSMTP(this.host, this.port, this.username, this.password);
            this.strategies.put(sgsmtp.getStrategyName(), sgsmtp);

        }

        if(this.currentStrategy == null)
            this.currentStrategy = this.strategies.get(MailerStrategyName.SENDGRIDWEBAPI);

        // THIS IS COMMENTED FOR TESTING PURPOSES
        // IN PRODUCTION YOU SHOULD UNCOMMENT THIS FOR SENDING EMAILS
        // this.currentStrategy.send(email);
    }

}
