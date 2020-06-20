package backendApplication.model.emailBuilder;

/**
 * Class that represents the Director. Constructs an object using the EmailBuilder interface.
 * */
public class Builder {
    /* Represents the concrete class */
    private EmailBuilder emailBuilder;

    /**
     * Parametrized Constructor for Builder
     *
     * @param eb EmailBuilder represents the concreteBuilder.
     * */
    public Builder(EmailBuilder eb) {
        this.emailBuilder = eb;
    }

    public void setEmailBuilder(EmailBuilder emailBuilder) {
        this.emailBuilder = emailBuilder;
    }

    /**
     * Method for creating an email.
     *
     * @param from String that has information for from
     * @param to String that has information for to
     * @param subject String that has information for subject
     * @param text String that has information for text
     *
     * @return Email Constructed email
     * */
    public Email createEmail(String from, String to, String subject, String text) {
        emailBuilder.createNewEmail();
        emailBuilder.setFrom(from);
        emailBuilder.setTo(to);
        emailBuilder.setSubject(subject);
        emailBuilder.setText(text);

        return emailBuilder.getEmail();
    }

}
