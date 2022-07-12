package bigdata.progetto2.servizioA.rest;

import bigdata.progetto2.servizioA.domain.*;

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
public class ServizioAController {

	@Autowired 
	private UtenzaService utenzaService; 

	private final Logger logger = Logger.getLogger(ServizioAController.class.toString()); 

	/* Crea una nuova nuova utenza*/
	@PostMapping("/nuovautenza")
	public Utenza createUtenza(@RequestBody CreateUtenzaRequest request) {
		String nome = request.getNome();
		String email = request.getEmail();
		logger.info("REST CALL: nuovautenza " + nome + ", " + email); 
		Utenza utenza = utenzaService.createUtenza(nome, email);
		return utenza; 
	}	

	/* ritorna tutte le utenze*/
	@GetMapping("/utenze")
	public Collection<Utenza> getUtenze() {
		Collection<Utenza> utenze = null; 		
		utenze = utenzaService.getUtenze();
		return utenze;
	}
}
