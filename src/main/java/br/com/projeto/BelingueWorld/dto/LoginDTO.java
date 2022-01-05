package br.com.projeto.BelingueWorld.dto;

import lombok.Data;

@Data
public class LoginDTO {  // classe 

	private String id;
	private String nome;
	private String nascimento;
	private String idade;
	private String email;
	private String senha;

	public LoginDTO() {
		super();
		this.id = id;
		this.nome = nome;
		this.nascimento = nascimento;
		this.idade = idade;
		this.email = email;
		this.senha = senha;
	}

	public String getId() {  //Get = vai pegar valor da variavel
		return id;
	}
	
	public void setId(String id) { //Set = vai trocar valor da variavel
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String usuario) {
		this.nome = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNascimento() {
		return nascimento;
	}


	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}
	

	}