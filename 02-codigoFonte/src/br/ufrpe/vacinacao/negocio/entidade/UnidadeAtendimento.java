package br.ufrpe.vacinacao.negocio.entidade;

public class UnidadeAtendimento {
	private int id;
	private UnidadeFederativa uf;
	private String nome;
	private String endereco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UnidadeFederativa getUf() {
		return uf;
	}

	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
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

}
