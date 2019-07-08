package br.ufrpe.vacinacao.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.ufrpe.framework.transaction.SystemException;
import br.ufrpe.framework.transaction.TransactionManager;
import br.ufrpe.vacinacao.negocio.entidade.Laboratorio;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;

public class LoteDAO {

	public void insert(br.ufrpe.vacinacao.negocio.entidade.Lote lote) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("INSERT INTO PUBLIC.LOTE (Vacina, Laboratorio, numero, qtdeDose, ");
			sql.append("	dataVencimento, valor )  VALUE (?, ?, ? ,?, ?, ?) ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, lote.getVacina().getId());
			preStmt.setInt(2, lote.getLaboratorio().getId());
			preStmt.setString(3, lote.getNumero());
			preStmt.setInt(4, lote.getQuantidadeDose());
			Date dataVencimento = new Date( lote.getDataVencimento().getTimeInMillis() );
			preStmt.setDate(5, dataVencimento );
			preStmt.setDouble(6, lote.getValor());			
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	public List<Lote> list(Lote filtro) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		List<Lote> listaRetorno= new ArrayList<Lote>();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			if (filtro != null) {
				
				preStmt = connection.prepareStatement(
						"SELECT l.id, l.qtdeDose, l.dataVencimento, l.numero, l.valor "
						+ "		lab.id AS \"idLaboratorio\", lab.nome AS \"nomeLaboratorio\", "
						+ "		v.id AS \"idVac\", v.nome AS \"nomeVac\" "
						+ "FROM Lote l "
						+ "		INNER JOIN Laboratorio lab ON lab.id= l.laboratorio "
						+ "		INNER JOIN Vacina v ON v.id= l.vacina "					
						+ "WHERE unidAtend.unidadeFederativa= ? ");
				int idx= 1;
				
				if ( filtro.getNumero() != null )
					preStmt.setString(idx++, filtro.getNumero());
				
				rs = preStmt.executeQuery();
	
				while (rs.next()) {
					listaRetorno.add( carregar(rs) );
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
	
	private Lote carregar(ResultSet rs) throws SQLException {
		Lote lote= new Lote();
		lote.setId(rs.getInt("id"));
		lote.setNumero(rs.getString("numero"));
		lote.setQuantidadeDose(rs.getInt("qtdeDose"));
		
		Calendar dataVencimento= new GregorianCalendar();
		dataVencimento.setTime( rs.getDate("dataVencimento") );
		lote.setDataVencimento( dataVencimento );
		
		Vacina vacina= new Vacina();
		vacina.setId(rs.getInt("idVacina"));
		vacina.setNome(rs.getString("nomeVacina"));
		lote.setVacina(vacina);
		
		Laboratorio laboratorio= new Laboratorio();
		laboratorio.setId(rs.getInt("idLaboratorio"));
		laboratorio.setNome(rs.getString("nomeLaboratorio"));
		lote.setLaboratorio(laboratorio);
		
		return lote;
	}

	public void update(Lote lote) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("UPDATE PUBLIC.LOTE SET Vacina= ?, Laboratorio= ?, numero= ? ");
			sql.append("	qtdeDose= ?, dataVencimento= ?, valor= ? ");
			sql.append("WHERE ID= ? ");

			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, lote.getVacina().getId());
			preStmt.setInt(2, lote.getLaboratorio().getId());
			preStmt.setString(3, lote.getNumero());
			preStmt.setInt(4, lote.getQuantidadeDose());
			Date dataVencimento = new Date( lote.getDataVencimento().getTimeInMillis() );
			preStmt.setDate(5, dataVencimento );
			preStmt.setDouble(6, lote.getValor());			
			preStmt.setInt(7, lote.getId());
			preStmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SystemException("\n " + e.getMessage() + " - Codigo: "
					+ e.getErrorCode());
		} finally {
			transactionManager.closeConnection(connection);
		}
	}
	
	public void delete(int id) {
		Connection connection = null;
		PreparedStatement preStmt = null;
		TransactionManager transactionManager = TransactionManager.getInstance();
		StringBuilder sql= new StringBuilder();

		try {
			connection = (Connection) transactionManager.getConnection();
			
			sql.append("DELETE FROM PUBLIC.LOTE WHERE ID= ? ");
			preStmt= connection.prepareStatement(sql.toString());
			preStmt.setInt(1, id);
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
