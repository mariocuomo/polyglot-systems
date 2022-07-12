package bigdata.progetto2.servizioD.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface UtenzeRepository extends MongoRepository<UtenzaModel, Long> {
	
}