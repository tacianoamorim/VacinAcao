package br.ufrpe.framework.transaction;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TransactionManager {
	private Properties dataBaseProperties = new Properties();
	private Logger log = Logger.getLogger(TransactionManager.class);
	
	private static TransactionManager instance;
	
	public TransactionManager() {
		loadConfiguration();
	}
	
	public static TransactionManager getInstance() {
		if ( instance == null )
			instance= new TransactionManager();
		return instance;
	}

	private void loadConfiguration() {
		try {
			InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties");
			dataBaseProperties.load(input);
			dataBaseProperties.list(System.out);

		} catch (IOException ex) {
			throw new SystemException("Arquivo database.properties nao encontrado.", ex);
		}
	}

	public Connection getConnection() {
		Connection connection= null;
		try {
			log.info("Abrindo a conecao");
			
			Class.forName(dataBaseProperties.getProperty("driverClassName")); 
			
			String autoDDL= dataBaseProperties.getProperty("autoDDL");
			String url= dataBaseProperties.getProperty("url");
			if ("create".equalsIgnoreCase(autoDDL)) {
				url= url + 
					";INIT=" +
				    "RUNSCRIPT FROM '~/create.sql'\\;" +
				    "RUNSCRIPT FROM '~/populate.sql'";
			}
			
			connection= DriverManager.getConnection(url	,
					dataBaseProperties.getProperty("user"), 
					dataBaseProperties.getProperty("password"));
			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);			
		} finally {
			log.info("Conecao realizada");
		}
		return connection;
	}

	public void getTransaction(Connection connection) {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException ex) {
			throw new SystemException("Nao foi possivel iniciar um transacao", ex);
		}
	}

	public void doFinishTransaction(Connection connection, boolean sucess) {
		try {
			if (sucess) {
				connection.commit();

			} else {
				connection.rollback();
			}
			connection.setAutoCommit(true);

		} catch (SQLException e) {
			if (e.getMessage().contains("Duplicate")) {
				throw new SystemException("Erro ao tentar inserir um rigistro com id já existente.", e);
			} else {
				throw new SystemException("Nao foi possivel finalizar uma transacao", e);
			}
			

		} finally {
			closeConnection(connection);
		}
	}

	/**
	 * Libera uma conexao com o banco de dados.
	 */
	public void closeConnection(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (Exception ex) {
			throw new SystemException("Nao foi possivel fechar uma conexao");
		}
	}

}

