package bigdata.progetto2.servizioD.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import bigdata.progetto2.common.api.event.*;

import java.util.logging.*;
import bigdata.progetto2.common.api.event.DomainEvent;


@Service
public class EventHandler {

	private final Logger logger = Logger.getLogger(EventHandler.class.toString());
	

	@Autowired
	private UtenzaService utenzaService;
	
	public void onEvent(DomainEvent event) {
		UtenzaCreatedEvent utenza = (UtenzaCreatedEvent) event;
		utenzaCreated(utenza);
	}

	private void utenzaCreated(UtenzaCreatedEvent event) {
		UtenzaModel utenza = new UtenzaModel();
		utenza.setNome(event.getNome());
		utenza.setEmail(event.getEmail());
        
        utenzaService.createUtenza(utenza);
	}

}