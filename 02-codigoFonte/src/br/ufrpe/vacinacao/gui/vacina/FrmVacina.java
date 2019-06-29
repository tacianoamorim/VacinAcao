package br.ufrpe.vacinacao.gui.vacina;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.negocio.controlador.VacinaControl;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;


public class FrmVacina extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2294886200896618148L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTable tbLista;
	private FrmVacinaTableModel jTableModel;
	private JTextField txtNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmVacina dialog = new FrmVacina();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmVacina() {
		setTitle("Cadastro de vacina");
		setModal(true);
		setBounds(100, 100, 652, 421);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnLista = new JPanel();
		pnLista.setBounds(10, 11, 614, 145);
		contentPanel.add(pnLista);
		pnLista.setLayout(new BoxLayout(pnLista, BoxLayout.X_AXIS));	
		
		jTableModel = new FrmVacinaTableModel();
		
		tbLista = new JTable(jTableModel);
		formatarTabela(tbLista);	
		
		JScrollPane scpLista = new JScrollPane(tbLista);
		tbLista.setFillsViewportHeight(true);
		pnLista.add(scpLista);
		
		JLabel lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setBounds(20, 173, 75, 14);
		contentPanel.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(20, 191, 86, 25);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblUnidadeAtendimento = new JLabel("Nome:");
		lblUnidadeAtendimento.setBounds(130, 173, 182, 14);
		contentPanel.add(lblUnidadeAtendimento);
		
		JLabel lblNewLabel_2 = new JLabel("Prescri\u00E7\u00E3o:");
		lblNewLabel_2.setBounds(20, 228, 204, 14);
		contentPanel.add(lblNewLabel_2);
		
		JTextArea txtPrescricao = new JTextArea();
		txtPrescricao.setBounds(20, 248, 606, 90);
		contentPanel.add(txtPrescricao);
		
		txtNome = new JTextField();
		txtNome.setBounds(130, 191, 494, 25);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setIcon(new ImageIcon(FrmVacina.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
				okButton.setBackground(Color.WHITE);
				okButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				okButton.setForeground(new Color(34, 139, 34));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Vacina vacina= new Vacina();
						
						if ( !txtId.getText().equals("") && !txtId.getText().equalsIgnoreCase("0") ) {
							vacina.setId(Integer.parseInt(txtId.getText()));
						}
						
						if ( txtNome.getText() == null || txtNome.getText().length() == 0 || 
								txtPrescricao.getText() == null || txtPrescricao.getText().length() == 0) {
							JOptionPane.showMessageDialog(null, "Informe todos os dados.");
							return;
						}	

						vacina.setNome(txtNome.getText());
						vacina.setPrescricao(txtPrescricao.getText());
					
						VacinaControl.getInstance().insert(vacina);
						
						jTableModel.limpar();
						List<Vacina> lista= VacinaControl.getInstance().list(new Vacina());
						jTableModel.addList(lista);
						jTableModel.fireTableDataChanged();
						
					}
				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtId.setText("");
						txtNome.setText("");
						txtPrescricao.setText("");
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 11));
				cancelButton.setIcon(new ImageIcon(FrmVacina.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
				cancelButton.setSelectedIcon(new ImageIcon(FrmVacina.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
				cancelButton.setForeground(Color.RED);
				cancelButton.setBackground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		// Carregar lista
		List<Vacina> lista= VacinaControl.getInstance().list(new Vacina());
		jTableModel.addList(lista);
		
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(230);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(310);
	}		
}
