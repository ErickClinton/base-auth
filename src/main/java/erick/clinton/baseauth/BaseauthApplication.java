package erick.clinton.baseauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BaseauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseauthApplication.class, args);
	}

}
