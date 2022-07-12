package bigdata.progetto2.coordinatore.domain;

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
    private MongoTemplate mongoTemplate;

    public void createUtenza(UtenzaModel utenza) {
	    mongoTemplate.save(utenza);
	}
}
