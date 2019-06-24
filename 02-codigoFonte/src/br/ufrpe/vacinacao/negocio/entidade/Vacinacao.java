package br.ufrpe.vacinacao.negocio.entidade;

import java.util.Calendar;

public class Vacinacao {
	private int id;
	private Estoque estoque;
	private Servidor servidor;
	private Usuario usuario;
	private Calendar dataAplicacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Calendar getDataAplicacao() {
		return dataAplicacao;
	}

	public void setDataAplicacao(Calendar dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

}
