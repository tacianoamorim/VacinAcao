package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeFederativa;

public class UnidadeFederativaDAO {

	public List<UnidadeFederativa> list(UnidadeFederativa filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<UnidadeFederativa> listaRetorno= new ArrayList<UnidadeFederativa>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT Sigla, Nome FROM UNIDADEFEDERATIVA WHERE 0= 0 ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				sql.append("ORDER BY Sigla ");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome());
				if ( filtro.getSigla() != null )
					preStmt.setString(idx++, filtro.getSigla()+"");
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					UnidadeFederativa unidadeFederativa= new UnidadeFederativa();
					unidadeFederativa.setNome(rs.getString("nome"));
					unidadeFederativa.setSigla( rs.getString("sigla"));					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Código: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return listaRetorno;		
	}
	
}
