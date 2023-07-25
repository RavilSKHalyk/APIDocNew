package APIDocNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ApiDocNewApplication extends SpringBootServletInitializer {

	public class Constants {
		/**
		 * если TEST = true используется тестовый ЕСБД и ГБД
		 */
		public static final boolean TEST = false;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiDocNewApplication.class, args);

	}


}
