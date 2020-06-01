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
     * Abstract method for implementations set the From string
     *
     * @param from String that represents the from property.
     * */
    abstract void setFrom(String from);

    /**
     * Abstract method for implementations set the To string
     *
     * @param to String that represents the to property.
     * */
    abstract void setTo(String to);

    /**
     * Abstract method for implementations set the Subject string
     *
     * @param subject String that represents the subject property.
     * */
    abstract void setSubject(String subject);

    /**
     * Abstract method for implementations set the From string
     *
     * @param text String that represents the text property.
     * */
    abstract void setText(String text);
}
