package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.repositorio.LaboratorioDAO;
import br.ufrpe.vacinacao.negocio.entidade.Laboratorio;

public class LaboratorioControl {
	
	private LaboratorioDAO repositorio;
	private static LaboratorioControl instance;
	
	public LaboratorioControl() {
		repositorio= new LaboratorioDAO();
	}
	
	public static LaboratorioControl getInstance() {
		if ( instance == null )
			instance= (LaboratorioControl) TransactionProxy
					.getInstance(LaboratorioControl.class);
		return instance;
	}
	
	public List<Laboratorio> list() {
		return repositorio.list();
	}
	
}
