package br.ufrpe.vacinacao.negocio.entidade;

public class Vacina {
	private int id;
	private String nome;
	private String prescricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrescricao() {
		return prescricao;
	}

	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}

	@Override
	public String toString() {
		return id + "- " + nome;
	}

}
