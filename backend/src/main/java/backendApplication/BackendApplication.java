package backendApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BackendApplication extends SpringBootServletInitializer {

	private static final Logger log= LoggerFactory.getLogger(BackendApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BackendApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner demo(SendGridSMTP emailService) {
		return (args) -> {

			EmailDirector builder = new EmailDirector(new WelcomeEmail());
			Email email = builder.createEmail("jpsilva9898@gmail.com", "jpsilva9898@gmail.com", "jpsilva9898", null);

			emailService.send(email);
		};
	}*/

}
