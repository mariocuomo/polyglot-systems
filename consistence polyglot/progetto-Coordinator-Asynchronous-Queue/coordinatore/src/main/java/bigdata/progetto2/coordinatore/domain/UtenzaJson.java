package bigdata.progetto2.coordinatore.domain;

import lombok.*; 

@NoArgsConstructor
public class UtenzaJson {
	private String nome; 
	private String email; 

	public UtenzaJson(String nome, String email)   
	{   
		this.nome = nome;   
		this.email = email;
	}   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
}
