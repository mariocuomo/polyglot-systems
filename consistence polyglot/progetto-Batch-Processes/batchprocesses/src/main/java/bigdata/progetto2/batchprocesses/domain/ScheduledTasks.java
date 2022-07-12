package bigdata.progetto2.batchprocesses.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*; 


@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


	@Autowired 
	private UtenzaService utenzaService; 

	@Scheduled(fixedRate = 60000)
	public void syncData() {
		Collection<Utenza> utenze_relazionale = null; 		
		utenze_relazionale = utenzaService.getUtenze_relazionale();
		log.info("The time is now {}", dateFormat.format(new Date()));


		Collection<UtenzaModel> utenze_noSQL = null;
		utenze_noSQL = utenzaService.getUtenze_noSQL(); 		

		class Utente   
		{   
			private String nome; 
			private String email; 

			public Utente(String nome, String email)   
			{   
				this.nome = nome;   
				this.email = email;
			}   

		    public String getNome() {
		        return nome;
		    }

		    public void setNome(String nome) {
		        this.nome = nome;
		    }

			public String getEmail() {
		        return email;
		    }

		    public void setEmail(String email) {
		        this.email = email;
		    }

		    @Override
   			public boolean equals(Object o) {
				if (o == null) {
					return false;
				}

   				if (o == this)
   					return true;
        		
        		Utente user = (Utente)o;
        		return user.getNome().equals(nome) && user.getEmail().equals(email);
    		}

		    @Override
    		public int hashCode() {
        		return (nome+email).hashCode();
    		}
		}

		Set<Utente> set_relazionale = new HashSet<Utente> (); 
		
		for(Utenza user : utenze_relazionale ) {
			Utente utente = new Utente(user.getNome(), user.getEmail());
			set_relazionale.add(utente);
     	}

		Set<Utente> set_noSQL = new HashSet<Utente> (); 
		for(UtenzaModel user : utenze_noSQL ) {
			Utente utente = new Utente(user.getNome(), user.getEmail());
			set_noSQL.add(utente);
     	}

     	set_relazionale.removeAll(set_noSQL);

     	for(Utente user : set_relazionale ) {
			UtenzaModel utente = new UtenzaModel();
			utente.setNome(user.getNome());
			utente.setEmail(user.getEmail());
			utenzaService.createUtenza(utente);
     	}
	}
}