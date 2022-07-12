package bigdata.progetto2.servizioA.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 

import java.util.*; 

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;

import java.io.IOException;


import java.util.logging.Logger; 

@Service
public class UtenzaService {

	@Autowired
	private UtenzeRepository utenzeRepository;


 	public Utenza createUtenza(String utente, String email){
		Utenza utenza = new Utenza(utente, email); 
		utenza = utenzeRepository.save(utenza);

		try {
			salvaInCoda(utente, email);
		}
		catch(Exception e) {
			System.out.println("=================ERRORE===========");
			System.out.println(e);

		}

		return utenza;
	}

	public Collection<Utenza> getUtenze() {
		Collection<Utenza> utenze = utenzeRepository.findAll();
		return utenze;
	}





	private void salvaInCoda(String utente, String email) throws IOException {
		Request request = Request.Post("http://codaasincrona:8081/inserisciutenzaincoda");
		String body = "{ \"nome\": \"" + utente + "\", \"email\": \""+email+ "\"}";
		request.bodyString(body,ContentType.APPLICATION_JSON);
		request.setHeader("Accept", "*/*");
		request.setHeader("Content-Type", "application/json");
		HttpResponse httpResponse = request.execute().returnResponse();
		System.out.println(httpResponse.getStatusLine());
		if (httpResponse.getEntity() != null) {
			String html = EntityUtils.toString(httpResponse.getEntity());
			System.out.println(html);
		}
	}

}
