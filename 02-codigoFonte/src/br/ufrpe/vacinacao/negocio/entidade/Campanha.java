package br.ufrpe.vacinacao.negocio.entidade;

import java.sql.Date;

public class Campanha {
	private int codigo;
	private String nome;
	private int vacinaID;
	private double valor;
	private Date dataInicioDivulgacao;
	private Date dataFimDivulgacao;
	private Date dataInicioExecucao;
	private Date dataFimExecucao;
	private String observacao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getDataInicioDivulgacao() {
		return dataInicioDivulgacao;
	}

	public void setDataInicioDivulgacao(Date dataInicioDivulgacao) {
		this.dataInicioDivulgacao = dataInicioDivulgacao;
	}

	public Date getDataFimDivulgacao() {
		return dataFimDivulgacao;
	}

	public void setDataFimDivulgacao(Date dataFimDivulgacao) {
		this.dataFimDivulgacao = dataFimDivulgacao;
	}

	public Date getDataInicioExecucao() {
		return dataInicioExecucao;
	}

	public void setDataInicioExecucao(Date dataInicioExecucao) {
		this.dataInicioExecucao = dataInicioExecucao;
	}

	public Date getDataFimExecucao() {
		return dataFimExecucao;
	}

	public void setDataFimExecucao(Date dataFimExecucao) {
		this.dataFimExecucao = dataFimExecucao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getVacina() {
		return vacinaID;
	}

	public void setVacina(int vacina) {
		this.vacinaID = vacina;
	}

	
}
