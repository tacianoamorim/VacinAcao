package br.ufrpe.vacinacao.negocio.entidade;

import java.util.Calendar;

public class Lote {
	private int id;
	private Vacina vacina;
	private Laboratorio laboratorio;
	private String numero;
	private int quantidadeDose;
	private Calendar dataVencimento;
	private double valor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public int getQuantidadeDose() {
		return quantidadeDose;
	}

	public void setQuantidadeDose(int quantidadeDose) {
		this.quantidadeDose = quantidadeDose;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
