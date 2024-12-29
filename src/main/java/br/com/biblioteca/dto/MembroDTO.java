package br.com.biblioteca.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MembroDTO {

	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 100, message = "Nome não pode ter mais de 100 caracteres")
	private String nome;

	@NotEmpty(message = "Cargo é obrigatório")
	@Size(max = 100, message = "Cargo não pode ter mais de 100 caracteres")
	private String cargo;

	// Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
