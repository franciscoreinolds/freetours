package backendApplication.model.emailBuilder;

/**
 * Class represents a Concrete Builder, in this case specifies a Scheduling Cancellation Email.
 * */
public class SchedulingCancellation extends EmailBuilder{

    @Override
    protected void setSubject(String subject) {

        email.setSubject("A Scheduling Was Canceled!");

    }

    @Override
    protected void setText(String text) {

        email.setText(
                "Hey!\n\n" +
                "It is with great regret that we inform you that one of your schedulings has been canceled.\n" +
                "We refer to the scheduling of the tour " + text + ".\n\n" +
                "We are sorry for what happened but the guide will not be able to guarantee this schedule.\n" +
                "Continue to search for tours on our website.\n\n" +
                "Best Regards,\nFreeTours Team\n"
        );

    }

}
