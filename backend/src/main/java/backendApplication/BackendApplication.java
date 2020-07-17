package backendApplication;

import backendApplication.model.QRCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner demo(QRCodeService qrCodeService) {
		return (args) -> {

			String image = qrCodeService.getQRCode(1);

			System.out.println(image);

		};
	}*/

}
