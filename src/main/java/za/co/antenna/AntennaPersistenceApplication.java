package za.co.antenna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// SpringBootKanbanBoardApplication


@SpringBootApplication
public class AntennaPersistenceApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(AntennaPersistenceApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AntennaPersistenceApplication.class);
	}
	

}
