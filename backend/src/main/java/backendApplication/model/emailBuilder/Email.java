package backendApplication.model.emailBuilder;

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

    protected Email() {
        this.from = "";
        this.to = "";
        this.subject = "";
        this.text = "";
    }

    protected Email(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    protected void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    protected void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    protected void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    protected void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "######################################\n" +
                "# Email:\n" +
                "#   from='" + from + "'\n" +
                "#   to='" + to + "'\n" +
                "#   subject='" + subject + "'\n" +
                "#   text='" + text + "'\n" +
                "######################################";
    }
}
