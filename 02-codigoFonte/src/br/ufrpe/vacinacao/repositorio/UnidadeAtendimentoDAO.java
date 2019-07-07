package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;

public class UnidadeAtendimentoDAO {

	public List<UnidadeAtendimento> list(UnidadeAtendimento filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<UnidadeAtendimento> listaRetorno= new ArrayList<UnidadeAtendimento>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
				sql.append("SELECT Id, Nome FROM UnidadeAtendimento WHERE 0= 0 ");
				if ( filtro.getUnidadeFederativa() != null && filtro.getUnidadeFederativa().getSigla() != null)
					sql.append("AND sigla= ? ");
				if ( filtro.getNome() != null )
					sql.append("AND nome like '%?%'");
				sql.append("ORDER BY Nome ");
				
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
				
				if ( filtro.getUnidadeFederativa() != null && filtro.getUnidadeFederativa().getSigla() != null)
					preStmt.setString(idx++, filtro.getUnidadeFederativa().getSigla());
				if ( filtro.getNome() != null )
					preStmt.setString(idx++, filtro.getNome()+"");
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					UnidadeAtendimento unidadeAtendimento= new UnidadeAtendimento();
					unidadeAtendimento.setId(rs.getInt("id"));
					unidadeAtendimento.setNome( rs.getString("nome"));	
					listaRetorno.add(unidadeAtendimento);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
		return listaRetorno;		
	}
	
}
