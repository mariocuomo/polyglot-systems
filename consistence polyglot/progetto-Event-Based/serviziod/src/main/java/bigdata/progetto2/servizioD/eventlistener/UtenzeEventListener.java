package bigdata.progetto2.servizioD.eventlistener;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import bigdata.progetto2.servizioD.domain.EventHandler;
import bigdata.progetto2.common.api.event.DomainEvent;


@Component
public class UtenzeEventListener {

	@Autowired
	private EventHandler eventHandler;

	@KafkaListener(topics = "${bigdata.progetto2.kafka.channel.in}", groupId="${bigdata.progetto2.kafka.groupid}")
	public void listen(ConsumerRecord<String, DomainEvent> record) {
		eventHandler.onEvent(record.value());
	}

}