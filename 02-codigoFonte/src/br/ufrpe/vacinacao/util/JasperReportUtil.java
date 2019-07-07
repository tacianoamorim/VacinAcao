package br.ufrpe.vacinacao.util;

import java.sql.SQLException;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.view.JasperViewer;

public interface JasperReportUtil {

	public static void gerar(JasperDesign jasperDesign, JRBeanCollectionDataSource dataSource, Map<String, Object> parametros ) 
			throws JRException , SQLException, ClassNotFoundException {
		 try {
			 //compila o relatório
			 JasperReport relatorio = JasperCompileManager.compileReport( jasperDesign );
			 
			 //executa o relatório
			 JasperPrint impressao = JasperFillManager.fillReport( relatorio, parametros, dataSource);
			 
			 //exibe o resultado
			 JasperViewer viewer = new JasperViewer( impressao , true );
			 viewer.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
