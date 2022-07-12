package bigdata.progetto2.codaasincrona.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 

@Service
public class QueueService {
	
	@Autowired 
	private CodaAggiornamenti queue; 

 	public Utenza aggiungiInCoda(String utente, String email) {
		Utenza utenza = new Utenza(utente, email); 
		queue.add(utenza);
		return utenza;
	}

 	public Collection<Utenza> estradiDallaCoda() {
		return queue.getQueueAndClean();

	}

	public Collection<Utenza> visualizzaCoda() {
		return queue.getQueue();
	}
}
