package backendApplication.unitTests;

import backendApplication.model.emailBuilder.Builder;
import backendApplication.model.emailBuilder.Email;
import backendApplication.model.emailBuilder.EmailBuilder;
import backendApplication.model.emailBuilder.WelcomeEmail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmailBuilderTests {

    @Test
    public void settingPartShouldNotThrowExceptions() {

        EmailBuilder builder = new WelcomeEmail();

        builder.setFrom("freetour@example.com");
        assertEquals("freetour@example.com", builder.getEmail().getFrom(), "setFrom Method");

        builder.setTo("user1@example.com");
        assertEquals("user1@example.com", builder.getEmail().getTo(), "setTo Method");

    }

    @Test
    public void constructWelcomeEmail() {

        Builder builder = new Builder(new WelcomeEmail());

        Email email = builder.createEmail("no-reply@freetours.example.com", "user1@example.com", "user1", null);
        assertNotNull(email, "Email construction");
        System.out.println(email.toString());

    }

}
