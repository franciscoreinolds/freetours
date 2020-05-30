package backendApplication;

import backendApplication.model.dao.*;
import backendApplication.model.entities.Category;
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
	public CommandLineRunner demo(CategoryService service) {
		return (args) -> {
			service.save(new Category("Bike Tour"));

			for (Category category : service.listAll()) {
				System.out.println(category.getName());
			}
		};
	}

}
