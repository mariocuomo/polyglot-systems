package bigdata.progetto2.servizioA.rest;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUtenzaRequest {
	private String nome; 
	private String email; 
}

