package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Servidor;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeFederativa;

public class ServidorDAO {

	public Servidor findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Servidor servidor= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			sql.append("SELECT s.matricula, s.nome, s.senha "
					+ "	ua.id AS 'idUA', uf.sigla "
					+ "FROM SERVIDOR s "
					+ "	INNER JOIN UNIDADEATENDIMENTO ua ON ua.id= s.unidadeAtendimento "
					+ " INNER JOIN UNIDADEFEDERATIVA uf ON uf.sigla= ua.unidadeFederativa "
					+ "WHERE matricula= ?");
			preStmt = connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				servidor = carregarServidor(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return servidor;		
	}

	private Servidor carregarServidor(ResultSet rs) throws SQLException {
		Servidor servidor= new Servidor();
		servidor.setMatricula(rs.getInt("matricula"));
		servidor.setNome(rs.getString("nome"));
		servidor.setSenha(rs.getString("senha"));
		
		UnidadeFederativa unidadeFederativa= new UnidadeFederativa();
		unidadeFederativa.setSigla(rs.getString("sigla"));
		
		UnidadeAtendimento unidadeAtendimento= new UnidadeAtendimento();
		unidadeAtendimento.setId(rs.getInt("id"));
		unidadeAtendimento.setUnidadeFederativa(unidadeFederativa);
		
		servidor.setUnidadeAtendimento(unidadeAtendimento);
		
		return servidor;
	}
	
	public Servidor findByFilter(Servidor filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Servidor servidor= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT s.matricula, s.nome, s.senha, "
						+ "	ua.id, uf.sigla "
						+ "FROM SERVIDOR s "
						+ "	INNER JOIN UNIDADEATENDIMENTO ua ON ua.id= s.unidadeAtendimento "
						+ " INNER JOIN UNIDADEFEDERATIVA uf ON uf.sigla= ua.unidadeFederativa "
						+ "WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				
				preStmt = connection.prepareStatement(sql.toString());
				int matriculax= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(matriculax++, filtro.getNome());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					servidor = carregarServidor(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - C�digo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return servidor;		
	}
	
	
	public void inserir() {
		System.out.println("Inserir Repositorio");
	}

}
