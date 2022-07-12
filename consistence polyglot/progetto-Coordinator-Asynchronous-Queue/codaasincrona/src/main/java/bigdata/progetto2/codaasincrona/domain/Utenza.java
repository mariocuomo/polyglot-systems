package bigdata.progetto2.codaasincrona.domain;

import lombok.*; 

@Data @NoArgsConstructor
public class Utenza {
	private String nome; 
	private String email; 
	
	public Utenza(String nome, String email) {
		this(); 
		this.nome = nome; 
		this.email = email; 
	}
	
}
