package bigdata.progetto2.codaasincrona.rest;

import bigdata.progetto2.codaasincrona.domain.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger; 
import java.util.*; 

@RestController
public class CodaasincronaController {

	@Autowired 
	private QueueService queueService; 

	private final Logger logger = Logger.getLogger(CodaasincronaController.class.toString()); 

	/* Inserisce in coda una nuova utenza*/
	@PostMapping("/inserisciutenzaincoda")
	public Utenza inserisciUtenzaInCoda(@RequestBody CreateUtenzaRequest request) {
		String nome = request.getNome();
		String email = request.getEmail();

		logger.info("REST CALL: inserisciUtenzaInCoda " + nome + ", " + email); 
		Utenza utenza = queueService.aggiungiInCoda(nome, email);
		return utenza; 
	}	

	/*ritorna il contenuto della coda*/
	@GetMapping("/elementiincoda")
	public Collection<Utenza> getUtenze() {
		Collection<Utenza> utenze = null; 	
		utenze = queueService.estradiDallaCoda();
		return utenze;
	}

	/*ritorna il contenuto della coda*/
	@GetMapping("/visualizzacoda")
	public Collection<Utenza> visualizzaUtenze() {
		Collection<Utenza> utenze = null; 	
		utenze = queueService.visualizzaCoda();
		return utenze;
	}	
}
