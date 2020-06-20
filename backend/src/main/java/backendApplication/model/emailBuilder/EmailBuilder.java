package backendApplication.model.emailBuilder;

/**
 * Class that represents abstract builder.
 * Specifies an abstract interface for creating parts of a Product object.
 **/
public abstract class EmailBuilder {
    /* Email to be built */
    protected Email email;

    /**
     * Getter for the email instance property.
     *
     * @returns Email that is the current representation of the email
     * */
    public Email getEmail() {
        return email;
    }

    /**
     * Method for creating a new Email
     * */
    public void createNewEmail() {
        email = new Email();
    }

    /**
     * Method that sets From property of an email
     *
     * @param from String that represents the from property.
     * */
    public void setFrom(String from) {
        try {
            email.setFrom(from);
        } catch (NullPointerException npe) {
            createNewEmail();
            email.setFrom(from);
        }
    }

    /**
     * Method that sets To property of an email
     *
     * @param to String that represents the to property.
     * */
    public void setTo(String to) {
        try {
            email.setTo(to);
        } catch (NullPointerException npe) {
            createNewEmail();
            email.setTo(to);
        }
    }

    /**
     * Abstract method for implementations set the Subject string
     *
     * @param subject String that represents the subject property.
     * */
    protected abstract void setSubject(String subject);

    /**
     * Abstract method for implementations set the From string
     *
     * @param text String that represents the text property.
     * */
    protected abstract void setText(String text);
}
