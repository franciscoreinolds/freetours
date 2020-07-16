package backendApplication.model.emailBuilder;

/**
 * Class represents a Concrete Builder, in this case specifies a Review Email.
 * */
public class ReviewEmail extends EmailBuilder{

    @Override
    protected void setSubject(String subject) {

        email.setSubject("Did you like the visit you participated in?");

    }

    @Override
    protected void setText(String text) {

        email.setText(
                "Hey!\n\n" +
                "Give us your opinion about the visit you participated in. " +
                "Help other tourists decide which visit to choose based on the rating of the guides and the visits themselves.\n" +
                "Evaluate the visit by following the following link:\n" + text + "\n\n" +
                "Best Regards,\nFreeTours Team\n"
        );

    }

}
