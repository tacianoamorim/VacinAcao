package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Campanha;

public class CampanhaDAO {
	
	public void insert(Campanha campanha) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO PUBLIC.CAMPANHA (VACINA, NOME, VALORTOTAL, DATAINICIODIVULGACAO, DATAFIMDIVULGACAO, DATAINICIOEXECUCAO, DATAFIMEXECUCAO, OBSERVACAO) ");
			sql.append("VALUES(?, ?, ?, ?, ?, ?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());			
			preStmt.setInt(1, campanha.getVacina().getId());
			preStmt.setString(2, campanha.getNome());
			preStmt.setDouble(3, campanha.getValor());
			preStmt.setDate(4, campanha.getDataInicioDivulgacao());
			preStmt.setDate(5, campanha.getDataFimDivulgacao());
			preStmt.setDate(6, campanha.getDataInicioExecucao());
			preStmt.setDate(7, campanha.getDataFimExecucao());
			preStmt.setString(8, campanha.getObservacao());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - C�digo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("DELETE FROM PUBLIC.CAMPANHA WHERE ID= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
}
