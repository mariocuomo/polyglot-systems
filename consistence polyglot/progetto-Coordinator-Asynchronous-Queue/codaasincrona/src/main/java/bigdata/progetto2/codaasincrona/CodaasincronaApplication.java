package bigdata.progetto2.codaasincrona;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class CodaasincronaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodaasincronaApplication.class, args);

		
	}
}
