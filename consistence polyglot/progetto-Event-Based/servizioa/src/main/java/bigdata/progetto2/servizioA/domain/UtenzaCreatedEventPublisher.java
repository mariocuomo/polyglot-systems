package bigdata.progetto2.servizioA.domain;

import bigdata.progetto2.common.api.event.DomainEvent;

public interface UtenzaCreatedEventPublisher {

	public void publish(DomainEvent event);

}