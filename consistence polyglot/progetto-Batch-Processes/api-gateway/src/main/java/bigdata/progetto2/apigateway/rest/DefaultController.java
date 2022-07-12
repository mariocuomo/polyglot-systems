package bigdata.progetto2.apigateway.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*; 

@RestController
public class DefaultController {

	@GetMapping("/")
	public Collection<String> index() {
		return null;
	}
	
}
