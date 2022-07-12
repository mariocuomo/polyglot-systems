package bigdata.progetto2.servizioA.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 


import bigdata.progetto2.common.api.event.*; 

@Service
public class UtenzaService {

	@Autowired
	private UtenzeRepository utenzeRepository;

	@Autowired
	private UtenzaCreatedEventPublisher eventPublisher;

 	public Utenza createUtenza(String utente, String email) {
		Utenza utenza = new Utenza(utente, email); 
		utenza = utenzeRepository.save(utenza);

		DomainEvent eventPublisherUtenza = new UtenzaCreatedEvent (utenza.getNome(),utenza.getEmail());

		eventPublisher.publish(eventPublisherUtenza);

		return utenza;
	}

	public Collection<Utenza> getUtenze() {
		Collection<Utenza> utenze = utenzeRepository.findAll();
		return utenze;
	}

}
