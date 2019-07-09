package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Laboratorio;

public class LaboratorioDAO {

	public List<Laboratorio> list() {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Laboratorio> listaRetorno= new ArrayList<Laboratorio>();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			sql.append("SELECT id, nome FROM PUBLIC.LABORATORIO ORDER BY Nome  ");
			preStmt = connection.prepareStatement(sql.toString());
			rs = preStmt.executeQuery();
			while (rs.next()) {
				Laboratorio laboratorio= new Laboratorio();
				laboratorio.setId(rs.getInt("id"));
				laboratorio.setNome(rs.getString("nome"));
				listaRetorno.add( laboratorio );
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
