package br.ufrpe.vacinacao.negocio.controlador;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.Usuario;
import br.ufrpe.vacinacao.repositorio.UsuarioDAO;

public class UsuarioControl {
	
	private UsuarioDAO repositorio;
	private static UsuarioControl instance;
	
	public UsuarioControl() {
		repositorio= new UsuarioDAO();
	}
	
	public static UsuarioControl getInstance() {
		if ( instance == null )
			instance= (UsuarioControl) TransactionProxy
					.getInstance(UsuarioControl.class);
		return instance;
	}
	
	public void inserir() {
		System.out.println("Inserir Controlador");
		repositorio.inserir();
	}
	
	public Usuario findById(int id) {
		return repositorio.findById(id);
	}
	
	public Usuario findByFilter(Usuario filtro) {
		return repositorio.findByFilter(filtro);
	}
	
}
