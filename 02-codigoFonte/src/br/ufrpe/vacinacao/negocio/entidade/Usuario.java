package br.ufrpe.vacinacao.negocio.entidade;

import java.util.Calendar;

public class Usuario {
	private int id;
	private String numeroSUS;
	private String nome;
	private String endereco;
	private String sexo;
	private Calendar dataNascimento;
	private String senha;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroSUS() {
		return numeroSUS;
	}

	public void setNumeroSUS(String numeroSUS) {
		this.numeroSUS = numeroSUS;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
