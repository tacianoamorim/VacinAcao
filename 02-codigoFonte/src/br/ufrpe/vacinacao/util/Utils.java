package br.ufrpe.vacinacao.util;

import javax.swing.JOptionPane;

public class Utils {

	public static String getId(String texto, String separador) {
		String id= "";
		if ( texto != null && separador!= null) {
			String[] arrayText= texto.split(separador);
			id= arrayText[0];
		}
		return id;
	}
	
	
	public static void msgExcption(String msg) {
		JOptionPane.showMessageDialog(null, "Ocorreu o seuing erro ao gravar os dados: "
			+ msg, "ERROR", 
			JOptionPane.ERROR_MESSAGE);	
	}
	
}
