package bigdata.progetto2.common.api.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtenzaCreatedEvent implements DomainEvent {
	private String nome; 
	private String email; 
	
}