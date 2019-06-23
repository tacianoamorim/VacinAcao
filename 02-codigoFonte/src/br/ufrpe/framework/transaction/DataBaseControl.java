package br.ufrpe.framework.transaction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataBaseControl {
	
	private static DataBaseControl instance;
	private Properties dataBaseProperties = new Properties();
	
	public DataBaseControl() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties");
			dataBaseProperties.load(input);

		} catch (IOException ex) {
			throw new SystemException("Arquivo database.properties nao encontrado.", ex);
		}		
	}
	
	public static DataBaseControl getInstance() {
		if ( instance == null )
			instance= (DataBaseControl) TransactionProxy
					.getInstance(DataBaseControl.class);
		return instance;
	}
	
}
