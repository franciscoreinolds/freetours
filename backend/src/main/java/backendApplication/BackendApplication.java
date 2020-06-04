package backendApplication;

import backendApplication.model.dao.*;
import backendApplication.model.entities.Administrator;
import backendApplication.model.entities.Category;
import backendApplication.model.entities.City;
import backendApplication.model.entities.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	private static final Logger log= LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CountryService countryservice) {
		return (args) -> {
			countryservice.save(new Country("Espanha"));

			//for (Administrator admin : service.listAll()) {
			//	System.out.println(admin.getEmail());
			//}
		};
	}

}
