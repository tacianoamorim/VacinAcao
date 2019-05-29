package br.ufrpe.spjc.negocio.controlador;

import br.ufrpe.framework.transaction.TransactionProxy;
import br.ufrpe.spjc.repositorio.RepositorioJuizado;

public class ControladorJuizado {
	
	private RepositorioJuizado repositorio;
	private static ControladorJuizado instance;
	
	public ControladorJuizado() {
		repositorio= new RepositorioJuizado();
	}
	
	public static ControladorJuizado getInstance() {
		if ( instance == null )
			instance= (ControladorJuizado) TransactionProxy
					.getInstance(ControladorJuizado.class);
		return instance;
	}
	
	public void inserir() {
		System.out.println("Inserir Controlador");
		repositorio.inserir();
	}
	
}
