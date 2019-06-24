package br.ufrpe.vacinacao.negocio.controlador;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.vacinacao.negocio.entidade.Servidor;
import br.ufrpe.vacinacao.repositorio.ServidorDAO;

public class ServidorControl {
	
	private ServidorDAO repositorio;
	private static ServidorControl instance;
	
	public ServidorControl() {
		repositorio= new ServidorDAO();
	}
	
	public static ServidorControl getInstance() {
		if ( instance == null )
			instance= (ServidorControl) TransactionProxy
					.getInstance(ServidorControl.class);
		return instance;
	}
	
	public void inserir() {
		System.out.println("Inserir Controlador");
		repositorio.inserir();
	}
	
	public Servidor findById(int id) {
		return repositorio.findById(id);
	}
	
	public Servidor findByFilter(Servidor filtro) {
		return repositorio.findByFilter(filtro);
	}
	
}
