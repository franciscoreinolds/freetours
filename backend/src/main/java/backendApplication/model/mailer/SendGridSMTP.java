package backendApplication.model.mailer;

import backendApplication.model.emailBuilder.Email;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configurable
public class SendGridSMTP implements MailerStrategy {
    private final String host;
    private final Integer port;
    private final String username;
    private final String password;

    public SendGridSMTP(String host, Integer port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    @Override
    public void send(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        getJavaMailSender().send(message);
    }

    @Override
    public MailerStrategyName getStrategyName() {
        return MailerStrategyName.SENDGRIDSMTP;
    }

    private JavaMailSender getJavaMailSender() {

        System.out.println(host);
        System.out.println(port);
        System.out.println(username);
        System.out.println(password);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
