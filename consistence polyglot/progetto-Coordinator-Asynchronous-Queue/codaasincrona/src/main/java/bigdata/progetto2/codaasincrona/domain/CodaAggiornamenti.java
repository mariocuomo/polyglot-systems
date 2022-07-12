package bigdata.progetto2.codaasincrona.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 



@Service
public class CodaAggiornamenti {
  	private Queue<Utenza> queue;

 	public CodaAggiornamenti () {
 		queue = new LinkedList<>();
	}

	public void add(Utenza utenza){
 		queue.add(utenza);
	}

	public Collection<Utenza> getQueueAndClean(){
 		Queue<Utenza> copia_queue = new LinkedList<Utenza>(queue);
		queue.clear();

		return copia_queue;
	}

	public Collection<Utenza> getQueue(){
		return queue;
	}


}
