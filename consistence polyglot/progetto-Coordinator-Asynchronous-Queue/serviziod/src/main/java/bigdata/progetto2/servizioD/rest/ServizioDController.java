package bigdata.progetto2.servizioD.rest;

import bigdata.progetto2.servizioD.domain.*;

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
public class ServizioDController {

	@Autowired 
	private UtenzaService utenzaService; 

	private final Logger logger = Logger.getLogger(ServizioDController.class.toString()); 

	/* ritorna tutte le utenze*/
	@GetMapping("/utenze")
	public Collection<UtenzaModel> getUtenze() {
		Collection<UtenzaModel> utenze = utenzaService.getUtenze(); 		
		return utenze;
	}
}
