package br.ufrpe.vacinacao.gui.relatorioEstoque;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.gui.FrmLogin;
import br.ufrpe.vacinacao.negocio.controlador.EstoqueControl;
import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.util.JasperReportUtil;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.awt.Color;

public class FrmRelatorioEstoque extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmRelatorioEstoque dialog = new FrmRelatorioEstoque();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmRelatorioEstoque() {
		setModal(true);
		setTitle("Relatório de estoque");
		setBounds(100, 100, 477, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRelatrioEstoque = new JLabel("Relatório de Estoque");
			lblRelatrioEstoque.setHorizontalAlignment(SwingConstants.CENTER);
			lblRelatrioEstoque.setFont(new Font("Dialog", Font.BOLD, 18));
			lblRelatrioEstoque.setBounds(12, 12, 445, 23);
			contentPanel.add(lblRelatrioEstoque);
		}
		
		JLabel lblPorUnidadeDe = new JLabel("Por Unidade de Atendimento");
		lblPorUnidadeDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorUnidadeDe.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPorUnidadeDe.setBounds(12, 42, 445, 23);
		contentPanel.add(lblPorUnidadeDe);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Visualizar relatório");
				okButton.setForeground(new Color(0, 128, 0));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Map<String, Object> parametros = new HashMap<String, Object>();

							URL url= getClass().getResource("RelatorioEstoque.jrxml");
							
							List<Estoque> lista= EstoqueControl.getInstance().list(
									FrmLogin.servidorLogado.getUnidadeAtendimento().getUnidadeFederativa().getSigla());
							
							JRBeanCollectionDataSource dataSource = 
									new JRBeanCollectionDataSource(lista);

							//gerando o jasper design
							JasperDesign desenho= JRXmlLoader.load( url.getFile());
							
							
							JasperReportUtil.gerar(desenho, dataSource, parametros);
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Fechar");
				cancelButton.setForeground(new Color(255, 0, 0));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
