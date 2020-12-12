package cloud.heeki.oci;

import java.beans.BeanProperty;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;

@SpringBootApplication
public class OciApplication {

	public static void main(String[] args) {
		SpringApplication.run(OciApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		Customer c1 = new Customer("John", "Doe", "1970-01-01", "john.doe@heeki.cloud", "+15551234567", true);
		Gson g = new Gson();
		String s = "{\"given_name\":\"John\",\"family_name\":\"Doe\",\"birthdate\":\"1970-01-01\",\"email\":\"john.doe@heeki.cloud\",\"phone_number\":\"+15551234567\",\"phone_number_verified\":true}";
		Customer c2 = g.fromJson(s, Customer.class);
		return args -> {
			System.out.println(c1);
			System.out.println(c2);
		};
	}

}
