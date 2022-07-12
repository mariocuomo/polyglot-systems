package bigdata.progetto2.servizioD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient 
public class ServizioDApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServizioDApplication.class, args);
	}
}
