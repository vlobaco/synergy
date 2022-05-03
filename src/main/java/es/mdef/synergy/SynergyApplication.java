package es.mdef.synergy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(JavaConfiguration.class)
public class SynergyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SynergyApplication.class, args);
	}

}
