package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;

public class VacinaDAO {

	public void insert(Vacina vacina) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO PUBLIC.VACINA (NOME, PRESCRICAO) ");
			sql.append("VALUE (?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, vacina.getNome());
			preStmt.setString(2, vacina.getPrescricao());
			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	public List<Vacina> list(Vacina filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Vacina> listaRetorno= new ArrayList<Vacina>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
				
				sql.append("SELECT id, nome, prescricao FROM PUBLIC.VACINA WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				if ( filtro.getPrescricao() != null )
					sql.append("AND prescricao like '%?%'");				
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome());
				
				if ( filtro.getPrescricao() != null )
					preStmt.setString(idx++, filtro.getPrescricao());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					listaRetorno.add( carregar(rs) );
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - C�digo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return listaRetorno;		
	}	
	
	private Vacina carregar(ResultSet rs) throws SQLException {
		Vacina vacina= new Vacina();
		vacina.setId(rs.getInt("id"));
		vacina.setNome(rs.getString("nome"));
		vacina.setPrescricao(rs.getString("prescricao"));
		return vacina;
	}

	public void update(Vacina vacina) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("UPDATE PUBLIC.VACINA SET NOME= ?, PRESCRICAO= ? ");
			sql.append("WHERE ID= ? ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setString(1, vacina.getNome());
			preStmt.setString(2, vacina.getPrescricao());
			preStmt.setInt(3, vacina.getId());
			
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
