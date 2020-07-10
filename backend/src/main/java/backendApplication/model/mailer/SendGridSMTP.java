package backendApplication.model.mailer;

import backendApplication.model.emailBuilder.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendGridSMTP implements MailerStrategy {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void send(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        mailSender.send(message);
    }
}
