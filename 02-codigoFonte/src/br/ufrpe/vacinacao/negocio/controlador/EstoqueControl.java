package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeFederativa;
import br.ufrpe.vacinacao.repositorio.UnidadeFederativaDAO;

public class EstoqueControl {
	
	private UnidadeFederativaDAO repositorio;
	private static EstoqueControl instance;
	
	public EstoqueControl() {
		repositorio= new UnidadeFederativaDAO();
	}
	
	public static EstoqueControl getInstance() {
		if ( instance == null )
			instance= (EstoqueControl) TransactionProxy
					.getInstance(EstoqueControl.class);
		return instance;
	}
	
	public List<UnidadeFederativa> list(UnidadeFederativa filtro) {
		return repositorio.list(filtro);
	}
	
}
