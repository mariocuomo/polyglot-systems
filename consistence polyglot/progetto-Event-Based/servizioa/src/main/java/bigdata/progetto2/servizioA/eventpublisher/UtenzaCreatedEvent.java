package bigdata.progetto2.servizioA.eventpublisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import bigdata.progetto2.servizioA.domain.UtenzaCreatedEventPublisher;
import bigdata.progetto2.common.api.event.DomainEvent;

@Component
public class UtenzaCreatedEvent implements UtenzaCreatedEventPublisher {

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	@Value("${bigdata.progetto2.kafka.channel.out}")
	private String channel;

	public void publish(DomainEvent event) {
		template.send(channel, event);
	}
}