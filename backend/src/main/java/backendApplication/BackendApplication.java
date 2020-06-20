package backendApplication;

import backendApplication.model.dao.*;
import backendApplication.model.entities.*;
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

	/*
	@Bean
	public CommandLineRunner demo(CityService cityservice, CountryService countryservice) {
		return (args) -> {
			countryservice.save(new Country("Portugal"));

			Country c = countryservice.get(1);

			cityservice.save(new City("Famalicão", c));

			City city = cityservice.get(1);

			System.out.println(city.getName() + " belongs to " + city.getCountry().getName());

			//for (Administrator admin : service.listAll()) {
			//	System.out.println(admin.getEmail());
			//}
		};
	}
	*/
}
