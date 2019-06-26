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

public class EstoqueDAO {

	public List<Estoque> list(Estoque filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Estoque> listaRetorno= new ArrayList<Estoque>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
			
//				SELECT e.ID, e.QUANTIDADEDOSES
//				,un.ID AS "IdUA", un.NOME AS "NomeUA", un.UNIDADEFEDERATIVA AS "UnidadeFederativa"
//				,l.ID AS "IdLote", l.NUMERO AS "numeroLote", v.NOME AS "Vacina"
//			FROM ESTOQUE e 
//				INNER JOIN UNIDADEATENDIMENTO un ON e.UNIDADEATENDIMENTO= un.ID
//				INNER JOIN LOTE l ON l.ID= e.LOTE
//				INNER JOIN VACINA v ON v.ID= l.VACINA;				
//				sql.append("SELECT id, UnidadeAtendimento, lote, quantidadeDores ");
				sql.append("FROM  ");
				preStmt = connection.prepareStatement(sql.toString());
				int idx= 1;
	
				while (rs.next()) {
					Estoque estoque= new Estoque();
//					estoque.setNome(rs.getString("nome"));
//					estoque.setSigla( rs.getString("sigla"));					
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
