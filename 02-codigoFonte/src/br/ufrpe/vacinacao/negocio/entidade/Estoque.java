package br.ufrpe.vacinacao.negocio.entidade;

public class Estoque {
	private int id;
	private Lote lote;
	private int quantidadeDoses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public int getQuantidadeDoses() {
		return quantidadeDoses;
	}

	public void setQuantidadeDoses(int quantidadeDoses) {
		this.quantidadeDoses = quantidadeDoses;
	}

}
