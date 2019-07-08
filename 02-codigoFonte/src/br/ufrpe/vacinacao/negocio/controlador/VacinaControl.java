package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;
import br.ufrpe.vacinacao.repositorio.VacinaDAO;

public class VacinaControl {
	
	private VacinaDAO repositorio;
	private static VacinaControl instance;
	
	public VacinaControl() {
		repositorio= new VacinaDAO();
	}
	
	public static VacinaControl getInstance() {
		if ( instance == null )
			instance= (VacinaControl) TransactionProxy
					.getInstance(VacinaControl.class);
		return instance;
	}
	
	public void salvar(Vacina vacina) {
		if (vacina.getId() > 0)
			repositorio.update(vacina);
		else
			repositorio.insert(vacina);
	}
	
	public List<Vacina> list(Vacina filtro) {
		return repositorio.list(filtro);
	}
	
	public void apagar(Vacina entity) {
		repositorio.delete(entity.getId());
	}
}
