package br.ufrpe.vacinacao.gui.relatorioEstoque;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.util.JasperReportUtil;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class FrmRelatorioEstoque extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5287862733905941387L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtAno;
	private JComboBox<Integer> cbxSemestre;

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
		setTitle("Formulário Tipo II - Gerenciamento de DOCUMENTO");
		setBounds(100, 100, 477, 248);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblRelatrioResumidoDe = new JLabel("Relatório resumido de Pautas");
			lblRelatrioResumidoDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblRelatrioResumidoDe.setFont(new Font("Dialog", Font.BOLD, 18));
			lblRelatrioResumidoDe.setBounds(12, 12, 445, 23);
			contentPanel.add(lblRelatrioResumidoDe);
		}
		{
			JLabel lblAno = new JLabel("Ano:");
			lblAno.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAno.setBounds(46, 66, 85, 15);
			contentPanel.add(lblAno);
		}
		{
			JLabel lblSemestre = new JLabel("Semestre:");
			lblSemestre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSemestre.setBounds(46, 113, 85, 15);
			contentPanel.add(lblSemestre);
		}
		
		txtAno = new JTextField();
		txtAno.setBounds(149, 64, 124, 19);
		contentPanel.add(txtAno);
		txtAno.setColumns(10);
		
		cbxSemestre = new JComboBox<Integer>();
		cbxSemestre.setBounds(149, 108, 124, 24);
		cbxSemestre.addItem(1);
		cbxSemestre.addItem(2);
		contentPanel.add(cbxSemestre);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Visualizar relatório");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							Map<String, Object> parametros = new HashMap<String, Object>();
							parametros.put("ANO", txtAno.getText());
							
							if (cbxSemestre.getSelectedItem().equals("1")) {
								parametros.put("MES_INICIO", 1);
								parametros.put("MES_FIM", 6);
							} else {
								parametros.put("MES_INICIO", 7);
								parametros.put("MES_FIM", 12);								
							}
							
							URL url= getClass().getResource("RelatorioResumidoPautaAudiencia.jrxml");
							
							StringBuilder query= new StringBuilder();
							query.append("SELECT DISTINCT jpa.nome, ")
							.append(" jpa.dataAgendamento, ")
							.append(" jpa.hora, ")
							.append(" jpa.processo, ")
							.append(" prp.tipo,  ")
							.append(" prp.polo, ")
							.append(" prp.nomePTR ")
							.append(" FROM DBSPJC.juizadoPautaAudiencia jpa ")
							.append("   INNER JOIN DBSPJC.parteRepresentanteProcesso prp ON prp.processo= jpa.processo ")
							.append(" ORDER BY 1, 2, 3, 4, 5 ");
							
							//gerando o jasper design
							JasperDesign desenho= JRXmlLoader.load( url.getFile());
							
							JasperReportUtil.gerar(desenho, query.toString(), parametros);
							setVisible(false);
							
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
