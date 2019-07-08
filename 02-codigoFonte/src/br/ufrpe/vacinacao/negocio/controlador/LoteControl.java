package br.ufrpe.vacinacao.negocio.controlador;

import java.util.List;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.repositorio.LoteDAO;

public class LoteControl {
	
	private LoteDAO repositorio;
	private static LoteControl instance;
	
	public LoteControl() {
		repositorio= new LoteDAO();
	}
	
	public static LoteControl getInstance() {
		if ( instance == null )
			instance= (LoteControl) TransactionProxy
					.getInstance(LoteControl.class);
		return instance;
	}
	
	public void salvar(Lote lote) {
		if (lote.getId() > 0)
			repositorio.update(lote);
		else
			repositorio.insert(lote);
	}
	
	public List<Lote> list(Lote filtro) {
		return repositorio.list(filtro);
	}
	
	public void apagar(int id) {
		repositorio.delete(id);
	}
}
