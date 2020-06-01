package backendApplication.model.emailBuilder;

public class WelcomeEmail extends EmailBuilder {

    @Override
    protected void setSubject(String subject) {

        email.setSubject("Hey " + subject + "! Welcome to Freetours!");

    }

    @Override
    protected void setText(String text) {

        email.setSubject(
                "We're excited to help you out!\n\n" +
                "Enter into your Account and make start schedule your tours!\n\n" +
                "Best Regards,\nFreeTours Team\n"
        );

    }

}
