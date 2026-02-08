package example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"example.demo", "controller", "service", "repository", "config", "exception"})
public class MusicLibrarySpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicLibrarySpringApplication.class, args);
	}
}
