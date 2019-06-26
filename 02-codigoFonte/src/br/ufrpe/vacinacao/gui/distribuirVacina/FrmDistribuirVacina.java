package br.ufrpe.vacinacao.gui.distribuirVacina;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.negocio.controlador.UnidadeFederativaControl;
import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeFederativa;


public class FrmDistribuirVacina extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2294886200896618148L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTable tbLista;
	private FrmDistribuirVacinaTableModel resumidoTableModel;
	
	private List<UnidadeFederativa> listUnidadeFederativa= new ArrayList<UnidadeFederativa>();
	private JTextField TxtQuantidade;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmDistribuirVacina dialog = new FrmDistribuirVacina();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmDistribuirVacina() {
		setTitle("Distribui\u00E7\u00E3o de vacinas");
		setModal(true);
		setBounds(100, 100, 652, 370);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel pnLista = new JPanel();
		pnLista.setBounds(10, 11, 614, 145);
		contentPanel.add(pnLista);
		pnLista.setLayout(new BoxLayout(pnLista, BoxLayout.X_AXIS));	
		
		resumidoTableModel = new FrmDistribuirVacinaTableModel();
		
		tbLista = new JTable(resumidoTableModel);
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
		
		JComboBox<UnidadeAtendimento> cbxUnidadeAtendimento = new JComboBox<UnidadeAtendimento>();
		cbxUnidadeAtendimento.setBounds(130, 192, 494, 25);
		contentPanel.add(cbxUnidadeAtendimento);
		
		JLabel lblUnidadeAtendimento = new JLabel("Unidade Atendimento");
		lblUnidadeAtendimento.setBounds(130, 173, 182, 14);
		contentPanel.add(lblUnidadeAtendimento);
		
		TxtQuantidade = new JTextField();
		TxtQuantidade.setBounds(389, 247, 235, 25);
		contentPanel.add(TxtQuantidade);
		TxtQuantidade.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setBounds(389, 228, 204, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vacina - Lotes - Doses dispon\u00EDveis");
		lblNewLabel_2.setBounds(20, 228, 204, 14);
		contentPanel.add(lblNewLabel_2);
		
		JComboBox<Lote> cbxLotes = new JComboBox<Lote>();
		cbxLotes.setBounds(20, 249, 344, 25);
		contentPanel.add(cbxLotes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Estoque estoque= new Estoque();
						
						if ( !txtId.getText().equalsIgnoreCase("0") ) {
							estoque.setId(Integer.parseInt(txtId.getText()));
						}
						
						if ( TxtQuantidade.getText() == null || TxtQuantidade.getText().length() == 0 || cbxLotes.getSelectedIndex()==0 ||
								cbxUnidadeAtendimento.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(null, "Informe todos os dados.");
							return;
						}	

						estoque.setQuantidadeDoses( Integer.parseInt(TxtQuantidade.getText()));
						
						UnidadeAtendimento unidadeAtendimento= (UnidadeAtendimento) cbxUnidadeAtendimento.getSelectedItem();
						estoque.setUnidadeAtendimento(unidadeAtendimento);
						
						Lote lote= (Lote) cbxLotes.getSelectedItem();
						estoque.setLote(lote);
						
					
					}
				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		
		/**
		 * CARREGADR DADOS
		 */
		listUnidadeFederativa= UnidadeFederativaControl.getInstance().list(new UnidadeFederativa());
		for (UnidadeFederativa unidadeFederativa: listUnidadeFederativa) {
			
		}
		
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(200);
		jTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		jTable.getColumnModel().getColumn(4).setPreferredWidth(100);
	}		
}
