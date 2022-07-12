package bigdata.progetto2.batchprocesses.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 
import org.springframework.data.mongodb.core.MongoTemplate;

@Service
public class UtenzaService {

	@Autowired
	private UtenzeRepository utenzeRepository;

	public Collection<Utenza> getUtenze_relazionale() {
		Collection<Utenza> utenze = utenzeRepository.findAll();
		return utenze;
	}

    @Autowired
    private MongoTemplate mongoTemplate;

	public Collection<UtenzaModel> getUtenze_noSQL() {
	    return mongoTemplate.findAll(UtenzaModel.class);
	}

	public void createUtenza(UtenzaModel utenza) {
	    mongoTemplate.save(utenza);
	}

}
