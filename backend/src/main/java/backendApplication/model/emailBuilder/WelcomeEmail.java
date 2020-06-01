package backendApplication.model.emailBuilder;

/**
 * Class represents a Concrete Builder, in this case specifies a Welcome Email.
 * */
public class WelcomeEmail extends EmailBuilder {

    @Override
    protected void setSubject(String subject) {

        email.setSubject("Hey " + subject + "! Welcome to Freetours!");

    }

    @Override
    protected void setText(String text) {

        email.setText(
                "We're excited to help you out!\n\n" +
                "Enter into your Account and start scheduling your tours!\n\n" +
                "Best Regards,\nFreeTours Team\n"
        );

    }

}
