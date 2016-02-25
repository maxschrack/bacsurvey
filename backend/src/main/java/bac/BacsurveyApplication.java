package bac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableAsync
public class BacsurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BacsurveyApplication.class, args);
	}
}
