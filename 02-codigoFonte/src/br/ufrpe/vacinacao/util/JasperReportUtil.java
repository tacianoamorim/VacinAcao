package br.ufrpe.vacinacao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

public interface JasperReportUtil {
	public static final String MYSQL_URL = "jdbc:mysql://localhost/DBSPJC";
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_LOGIN = "root";
	public static final String MYSQL_PWD = "";

	@SuppressWarnings("deprecation")
	public static void gerar( JasperDesign desenho, String query, Map<String, Object> parametros ) 
			throws JRException , SQLException, ClassNotFoundException {
		 
		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport( desenho );
		 
		//estabelece conexão
		Class.forName( MYSQL_DRIVER );
		Connection con = DriverManager.getConnection( MYSQL_URL , MYSQL_LOGIN , MYSQL_PWD );
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery( query );
		 
		//implementação da interface JRDataSource para DataSource ResultSet
		JRResultSetDataSource jrRS = new JRResultSetDataSource( rs );
		 
		//executa o relatório
		JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros );
		 
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( impressao , true );
		viewer.show();
	}
}
