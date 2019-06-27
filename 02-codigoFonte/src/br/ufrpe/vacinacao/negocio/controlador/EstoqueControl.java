package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.repositorio.EstoqueDAO;

public class EstoqueControl {
	
	private EstoqueDAO repositorio;
	private static EstoqueControl instance;
	
	public EstoqueControl() {
		repositorio= new EstoqueDAO();
	}
	
	public static EstoqueControl getInstance() {
		if ( instance == null )
			instance= (EstoqueControl) TransactionProxy
					.getInstance(EstoqueControl.class);
		return instance;
	}
	
	public List<Estoque> list(Estoque filtro) {
		return repositorio.list(filtro);
	}
	
}
