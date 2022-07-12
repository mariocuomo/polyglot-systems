package bigdata.progetto2.batchprocesses.domain;

import javax.persistence.*; 

import lombok.*; 

@Entity 
@Data @NoArgsConstructor
public class Utenza {

	private String nome; 

	@Id
	private String email; 
	
	public Utenza(String nome, String email) {
		this(); 
		this.nome = nome; 
		this.email = email; 
	}
	
}
