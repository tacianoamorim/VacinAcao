package br.ufrpe.vacinacao.negocio.entidade;

public class UnidadeFederativa {
	private String sigla;
	private String nome;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return sigla + "- " + nome;
	}

}
