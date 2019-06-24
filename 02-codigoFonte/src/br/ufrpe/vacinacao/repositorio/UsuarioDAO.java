package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Usuario;

public class UsuarioDAO {

	public Usuario findById(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Usuario usuario= null;

		try {
			connection = (Connection) transactionManager.getConnection();
			preStmt = connection.prepareStatement("SELECT id, nome, senha, numeroSUS FROM USUARIO WHERE id= ? ");
			preStmt.setInt(1, id);
			
			rs = preStmt.executeQuery();

			while (rs.next()) {
				usuario = carregarUsuario(rs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Código: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return usuario;		
	}

	private Usuario carregarUsuario(ResultSet rs) throws SQLException {
		Usuario usuario= new Usuario();
		usuario.setId(rs.getInt("id"));
		usuario.setNome(rs.getString("nome"));
		usuario.setSenha(rs.getString("senha"));
		usuario.setNumeroSUS(rs.getString("numeroSUS"));
		return usuario;
	}
	
	public Usuario findByFilter(Usuario filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		Usuario usuario= null;
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT id, nome, senha, numeroSUS FROM USUARIO WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				if ( filtro.getNumeroSUS() != null )
					sql.append("AND numeroSUS = ? ");				
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome());
				if ( filtro.getNumeroSUS() != null )
					preStmt.setString(idx++, filtro.getNumeroSUS());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					usuario = carregarUsuario(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Código: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return usuario;		
	}
	
	
	public void inserir() {
		System.out.println("Inserir Repositorio");
	}

}
