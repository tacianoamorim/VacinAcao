package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.expression.function.ToChar.Capitalization;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Campanha;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;

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
			throw new SystemException("\n " + e.getMessage() + " - Código: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	/*
	 * public List<Campanha> list(Campanha filtro) { Connection connection = null;
	 * PreparedStatement preStmt = null; ResultSet rs = null; TransactionManager
	 * transactionManager = TransactionManager.getInstance(); List<Campanha>
	 * listaRetorno= new ArrayList<Campanha>(); StringBuilder sql= new
	 * StringBuilder();
	 * 
	 * try { connection = (Connection) transactionManager.getConnection();
	 * 
	 * if (filtro != null) {
	 * 
	 * sql.
	 * append("SELECT id, vacina, nome, valorTotal, dataInicioDivulgacao, dataFimDivulgacao, dataInicioExecucao, dataFimExecucao FROM PUBLIC.Campanha WHERE 0= 0 "
	 * ); if ( filtro.getNome() != null ) sql.append("AND nome like '%?%'"); if (
	 * filtro.getPrescricao() != null ) sql.append("AND prescricao like '%?%'");
	 * 
	 * preStmt = connection.prepareStatement(sql.toString()); int idx= 1;
	 * 
	 * if ( filtro.getNome() != null ) preStmt.setString(idx++, filtro.getNome());
	 * 
	 * if ( filtro.getPrescricao() != null ) preStmt.setString(idx++,
	 * filtro.getPrescricao());
	 * 
	 * rs = preStmt.executeQuery();
	 * 
	 * while (rs.next()) { //listaRetorno.add( carregar(rs) ); } }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); throw new
	 * SystemException("\n " + e.getMessage() + " - Código: " + e.getErrorCode()); }
	 * finally { transactionManager.closeConnection(connection); } return
	 * listaRetorno; }
	 */
}
