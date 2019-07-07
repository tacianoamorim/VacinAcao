package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;
import br.ufrpe.vacinacao.repositorio.UnidadeAtendimentoDAO;

public class UnidadeAtendimentoControl {
	
	private UnidadeAtendimentoDAO repositorio;
	private static UnidadeAtendimentoControl instance;
	
	public UnidadeAtendimentoControl() {
		repositorio= new UnidadeAtendimentoDAO();
	}
	
	public static UnidadeAtendimentoControl getInstance() {
		if ( instance == null )
			instance= (UnidadeAtendimentoControl) TransactionProxy
					.getInstance(UnidadeAtendimentoControl.class);
		return instance;
	}
	
	public List<UnidadeAtendimento> list(UnidadeAtendimento filtro) {
		return repositorio.list(filtro);
	}
	
}
