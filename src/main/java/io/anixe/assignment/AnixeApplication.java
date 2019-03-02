package io.anixe.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
//@Profile("main")
public class AnixeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnixeApplication.class, args);
	}

}
