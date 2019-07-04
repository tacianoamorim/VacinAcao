package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;

public class EstoqueDAO {

	public List<Estoque> list(String uf) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Estoque> listaRetorno= new ArrayList<Estoque>();
		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (uf != null) {
				preStmt = connection.prepareStatement(
					"SELECT e.id, e.quantidadeDoses, "
					+ "		l.id AS 'idLote', l.nome AS 'nomeLote',"
					+ "		ua.id AS 'idUND', ua.nome AS 'nomeUND'	"
					+ "FROM Estoque e "
					+ "		INNER JOIN Lote l ON e.lote= l.id "
					+ "		INNER JOIN UnidadeAtendimento ua ON e.unidadeAtendimento= ua.id "
					+ "WHERE ua.unidadeFederativa= ? ");
				preStmt.setString(1, uf);
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					listaRetorno.add(carregarEntity(rs));				
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
	
	public Estoque findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Estoque entity= null;

		try {
			connection = (Connection) transactionManager.getConnection();
			preStmt = connection.prepareStatement(
					"SELECT e.id, e.quantidadeDoses, "
					+ "		l.id AS 'idLote', l.nome AS 'nomeLote',"
					+ "		ua.id AS 'idUND', ua.nome AS 'nomeUND'	"
					+ "FROM Estoque e "
					+ "		INNER JOIN Lote l ON e.lote= l.id "
					+ "		INNER JOIN UnidadeAtendimento ua ON e.unidadeAtendimento= ua.id "
					+ "WHERE e.id= ? ");
			preStmt.setInt(1, id);
			rs = preStmt.executeQuery();

			while (rs.next()) {
				entity = carregarEntity(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return entity;		
	}
	
	public void inserir(Estoque entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO Estoque ");
			sql.append(" (unidadeAtendimento, lote, quantidadeDoses) ");
			sql.append("VALUES (?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, entity.getUnidadeAtendimento().getId());
			preStmt.setInt(2, entity.getLote().getId());			
			preStmt.setInt(3, entity.getQuantidadeDoses());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	public void update(Estoque entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("UPDATE Estoque quantidadeDoses= ? ");
			sql.append("WHERE id= ? ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, entity.getQuantidadeDoses());
			preStmt.setInt(2, entity.getId());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}

	public void apagar(Estoque entity) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("DELETE FROM Estoque WHERE id= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, entity.getId());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}	
	
	private Estoque carregarEntity(ResultSet rs) throws SQLException {
		Estoque entity= new Estoque();
		entity.setId(rs.getInt("id"));
		entity.setQuantidadeDoses(rs.getInt("quantidadeDoses"));
		
		Lote lote= new Lote();
		lote.setId(rs.getInt("idLote"));
		lote.setNumero(rs.getString("numeroLote"));
		entity.setLote(lote);

		UnidadeAtendimento unidadeAtendimento= new UnidadeAtendimento();
		unidadeAtendimento.setId(rs.getInt("idUND"));
		unidadeAtendimento.setNome(rs.getString("nomeUND"));
		entity.setUnidadeAtendimento(unidadeAtendimento);
		
		return entity;
	}	
}
