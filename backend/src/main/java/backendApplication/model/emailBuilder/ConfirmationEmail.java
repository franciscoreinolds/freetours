package backendApplication.model.emailBuilder;

/**
 * Class represents a Concrete Builder, in this case specifies a Confirmation Email.
 * */
public class ConfirmationEmail extends EmailBuilder{

    @Override
    protected void setSubject(String subject) {

        email.setSubject("TODO");

    }

    @Override
    protected void setText(String text) {

        email.setText("TODO");

    }

}
