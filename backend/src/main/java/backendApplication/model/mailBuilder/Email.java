package backendApplication.model.mailBuilder;

/**
 * Class that represents the product. The complex class under construction.
 * */
public class Email {
    /* Who sends the email */
    private String from;
    /* Who receives the email */
    private String to;
    /* Email subject */
    private String subject;
    /* Email body */
    private String text;

    public Email() {
        this.from = "";
        this.to = "";
        this.subject = "";
        this.text = "";
    }

    public Email(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
