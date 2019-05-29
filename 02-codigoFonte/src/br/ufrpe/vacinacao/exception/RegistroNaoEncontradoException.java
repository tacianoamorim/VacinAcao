package br.ufrpe.spjc.exception;

public class RegistroNaoEncontradoException extends Exception {

	private String mensagem;
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2841557052455201538L;
	
	public RegistroNaoEncontradoException(String mensagem) {
		this.mensagem= mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
