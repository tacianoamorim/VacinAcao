package br.ufrpe.vacinacao.gui.distribuirVacina;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import br.ufrpe.vacinacao.gui.FrmLogin;
import br.ufrpe.vacinacao.negocio.controlador.EstoqueControl;
import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;
import br.ufrpe.vacinacao.util.Utils;


public class FrmDistribuirVacina extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2294886200896618148L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTable tbLista;
	private FrmDistribuirVacinaTableModel tableModel;
	
	private JTextField txtQtdeDoses;
	private JComboBox<Lote> cbxLotes;
	private JComboBox<UnidadeAtendimento> cbxUnidadeAtendimento;

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
		
		tableModel = new FrmDistribuirVacinaTableModel();
		
		tbLista = new JTable(tableModel);
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
		
		cbxUnidadeAtendimento = new JComboBox<UnidadeAtendimento>();
		cbxUnidadeAtendimento.setBounds(130, 192, 494, 25);
		contentPanel.add(cbxUnidadeAtendimento);
		
		JLabel lblUnidadeAtendimento = new JLabel("Unidade Atendimento");
		lblUnidadeAtendimento.setBounds(130, 173, 182, 14);
		contentPanel.add(lblUnidadeAtendimento);
		
		txtQtdeDoses = new JTextField();
		txtQtdeDoses.setBounds(389, 247, 235, 25);
		contentPanel.add(txtQtdeDoses);
		txtQtdeDoses.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Quantidade:");
		lblNewLabel_1.setBounds(389, 228, 204, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Vacina - Lotes - Doses dispon\u00EDveis");
		lblNewLabel_2.setBounds(20, 228, 323, 14);
		contentPanel.add(lblNewLabel_2);
		
		cbxLotes = new JComboBox<Lote>();
		cbxLotes.setBounds(20, 249, 344, 25);
		contentPanel.add(cbxLotes);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.setForeground(new Color(0, 128, 0));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Estoque estoque= new Estoque();
						
						if ( !txtId.getText().equalsIgnoreCase("0") ) {
							estoque.setId(Integer.parseInt(txtId.getText()));
						}
						
						if ( validaCampos() ) {
							estoque.setQuantidadeDoses( Integer.parseInt(txtQtdeDoses.getText()));
							
							UnidadeAtendimento unidadeAtendimento= (UnidadeAtendimento) cbxUnidadeAtendimento.getSelectedItem();
							estoque.setUnidadeAtendimento(unidadeAtendimento);
							
							Lote lote= (Lote) cbxLotes.getSelectedItem();
							estoque.setLote(lote);
						} else {
							JOptionPane.showMessageDialog(null, "Informe todos os dados.");
						}
					}
				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.setForeground(new Color(0, 0, 255));
				btnNovo.setBackground(new Color(255, 255, 255));
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						limpar();
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				
				JButton btnApagar = new JButton("Apagar");
				btnApagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
			                Estoque estoque= tableModel.get(tbLista.getSelectedRow());
			               
			                int selectedOption = JOptionPane.showConfirmDialog(null,"Confirma a exclusão?", 
			                		"Alerta", JOptionPane.YES_NO_OPTION);
			        		if(selectedOption == JOptionPane.YES_OPTION){
			        			EstoqueControl.getInstance().apagar(estoque);     
			        			carregarTable();
			        		}	
						} catch (Throwable ex) {
							ex.printStackTrace();
							Utils.msgExcption(ex.getMessage());	
						}						
					}
				});
				btnApagar.setForeground(new Color(255, 0, 0));
				btnApagar.setBackground(Color.WHITE);
				btnApagar.setActionCommand("OK");
				buttonPane.add(btnApagar);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton btnFechar = new JButton("Fechar");
				btnFechar.setBackground(new Color(255, 255, 255));
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnFechar.setActionCommand("Cancel");
				buttonPane.add(btnFechar);
			}
		}
	}
	
	private void limpar() {
		txtId.setText("");
		txtQtdeDoses.setText("");
		cbxLotes.setSelectedIndex(0);
		cbxUnidadeAtendimento.setSelectedIndex(0);
	}
	
	private void carregarDados(Estoque entity) {
		txtId.setText(entity.getId()+"");
		txtQtdeDoses.setText(entity.getQuantidadeDoses() +"");
	}
	
	private boolean validaCampos(){
		boolean isValido= true;
		if ( txtQtdeDoses.getText() == null || txtQtdeDoses.getText().length() == 0) {
			isValido= false;
		}
		return isValido;
	}	
	
	private void carregarTable() {
		tableModel.limpar();
		List<Estoque> lista= EstoqueControl.getInstance()
				.list(FrmLogin.servidorLogado.getUnidadeAtendimento().getUnidadeFederativa().getSigla());
		tableModel.add(lista);
		tableModel.fireTableDataChanged();
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
