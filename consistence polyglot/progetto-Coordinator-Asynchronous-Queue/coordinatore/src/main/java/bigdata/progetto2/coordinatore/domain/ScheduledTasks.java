package bigdata.progetto2.coordinatore.domain;

import org.springframework.scheduling.annotation.Scheduled;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*; 

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


	@Autowired 
	private UtenzaService utenzaService; 

	@Scheduled(fixedRate = 60000)
	public void syncData() {

		try {
			Request request = Request.Get("http://codaasincrona:8081/elementiincoda");
			HttpResponse httpResponse = request.execute().returnResponse();
			System.out.println(httpResponse.getStatusLine());
			if (httpResponse.getEntity() != null) {
				String html = EntityUtils.toString(httpResponse.getEntity());
				log.info("**************************");
				log.info(html);

				ObjectMapper mapper = new ObjectMapper();
				List<UtenzaJson> utenti = new ArrayList<>();
				utenti = (mapper.readValue(html, new TypeReference<List<UtenzaJson>>(){}));

				for(UtenzaJson user : utenti ) {
					UtenzaModel utente = new UtenzaModel();
					utente.setNome(user.getNome());
					utente.setEmail(user.getEmail());
					
					utenzaService.createUtenza(utente);
     			}

				System.out.println(html);
				log.info("OK");
			}
		}
		catch(Exception e) {
			log.info("ERRORE");
			log.info(e.getMessage());

		}
	}
}