package APIDocNew;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiDocNewApplication {
	public class Constants {
		/**
		 * если TEST = true используется тестовый ЕСБД и ГБД
		 */
		public static final boolean TEST = false;
	}

	public static void main(String[] args) throws Exception {

		SpringApplication.run(ApiDocNewApplication.class, args);

/*		ConfigurableApplicationContext context = SpringApplication.run(ApiDocNewApplication.class, args);
		String value = context.getEnvironment().getProperty("esbd.test.password");
		System.out.println(value);
		SessionESBD sessionESBD = new SessionESBD();
		System.out.println(sessionESBD.getSessionID());*/
	}
}
