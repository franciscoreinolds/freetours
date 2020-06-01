package backendApplication.unitTests;

import backendApplication.model.emailBuilder.EmailBuilder;
import backendApplication.model.emailBuilder.WelcomeEmail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailBuilderTests {

    @Test
    public void settingPartShouldNotThrowExceptions() {

        EmailBuilder builder = new WelcomeEmail();

        builder.setFrom("freetour@example.com");
        assertEquals("freetour@example.com", builder.getEmail().getFrom(), "setFrom Method");

        builder.setTo("user1@example.com");
        assertEquals("user1@example.com", builder.getEmail().getTo(), "setTo Method");

    }

}
