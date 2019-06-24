package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Servidor;

public class ServidorDAO {

	public Servidor findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Servidor servidor= null;

		try {
			connection = (Connection) transactionManager.getConnection();
			preStmt = connection.prepareStatement("SELECT matricula, nome, senha FROM SERVIDOR WHERE matricula= ? ");
			preStmt.setInt(1, id);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				servidor = carregarServidor(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Código: "
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
			
				sql.append("SELECT matricula, nome, senha, numeroSUS FROM USUARIO WHERE 0= 0 ");
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
			throw new SystemException("\n " + e.getMessage() + " - Código: "
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
