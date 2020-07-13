package backendApplication.model.mailer;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class SendGridWebAPI implements MailerStrategy {

    private final String appKey;

    public SendGridWebAPI(String appKey) {
        this.appKey = appKey;
    }

    @Override
    public void send(backendApplication.model.emailBuilder.Email email) {

        Email from = new Email(email.getFrom());
        Email to = new Email(email.getTo());
        Content content = new Content("text/plain", email.getText());
        Mail mail = new Mail(from, email.getSubject(), to, content);

        try {

            SendGrid sg = new SendGrid(this.appKey);

            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            // System.out.println(response.getStatusCode());
            // System.out.println(response.getBody());
            // System.out.println(response.getHeaders());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    @Override
    public MailerStrategyName getStrategyName() {
        return MailerStrategyName.SENDGRIDWEBAPI;
    }
}
