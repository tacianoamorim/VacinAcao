package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeFederativa;
import br.ufrpe.vacinacao.repositorio.UnidadeFederativaDAO;

public class UnidadeFederativaControl {
	
	private UnidadeFederativaDAO repositorio;
	private static UnidadeFederativaControl instance;
	
	public UnidadeFederativaControl() {
		repositorio= new UnidadeFederativaDAO();
	}
	
	public static UnidadeFederativaControl getInstance() {
		if ( instance == null )
			instance= (UnidadeFederativaControl) TransactionProxy
					.getInstance(UnidadeFederativaControl.class);
		return instance;
	}
	
	public List<UnidadeFederativa> list(UnidadeFederativa filtro) {
		return repositorio.list(filtro);
	}
	
}
