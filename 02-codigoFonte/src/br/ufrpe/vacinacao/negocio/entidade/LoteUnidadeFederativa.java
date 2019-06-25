package br.ufrpe.vacinacao.negocio.entidade;

import java.util.Calendar;

public class LoteUnidadeFederativa {
	private Lote lote;
	private UnidadeFederativa uf;
	private Calendar dataDistribuicao;
	private int qtdeDoses;

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public UnidadeFederativa getUf() {
		return uf;
	}

	public void setUf(UnidadeFederativa uf) {
		this.uf = uf;
	}

	public Calendar getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Calendar dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public int getQtdeDoses() {
		return qtdeDoses;
	}

	public void setQtdeDoses(int qtdeDoses) {
		this.qtdeDoses = qtdeDoses;
	}

}
