package bigdata.progetto2.batchprocesses.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface UtenzeRepository extends CrudRepository<Utenza, Long> {

	public Collection<Utenza> findAll();

}

