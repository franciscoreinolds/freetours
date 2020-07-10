package backendApplication.model.emailBuilder;

/**
 * Class represents a Concrete Builder, in this case specifies a Reset Password Email.
 * */
public class ResetPasswordEmail extends EmailBuilder{

    @Override
    protected void setSubject(String subject) {

        email.setSubject("Reset Your Password!");

    }

    @Override
    protected void setText(String text) {

        email.setText(
                "Hey!\n\n" +
                "It seems that you no longer remember you password.\n\n" +
                "Go to " + text +" and change your password.\n\n" +
                "Best Regards,\nFreeTours Team\n"
        );

    }

}
