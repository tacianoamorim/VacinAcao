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
	
	public List<Estoque> list(String uf) {
		return repositorio.list(uf);
	}

	public void apagar(Estoque entity) {
		repositorio.apagar(entity);		
	}
	
	public void salvar(Estoque entity) {
		if ( repositorio.findById(entity.getId()) == null ) {
			repositorio.inserir(entity);
		} else {
			repositorio.update(entity);
		}
	}	
}
