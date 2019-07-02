package br.ufrpe.vacinacao.negocio.entidade;

import java.sql.Date;
import java.time.LocalDate;

public class Campanha {
	private int codigo;
	private String nome;
	private Vacina vacina;
	private double valor;
	private Date dataInicioDivulgacao;
	private Date dataFimDivulgacao;
	private Date dataInicioExecucao;
	private Date dataFimExecucao;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	private String descricao;
}
