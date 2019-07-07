package br.ufrpe.vacinacao.gui.vacina;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import br.ufrpe.vacinacao.util.Utils;


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
	private JTextArea txtPrescricao;

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
		tbLista.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                Vacina entity= jTableModel.get(tbLista.getSelectedRow());
                limpar();
	            carregarDados(entity);
            }
            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
		
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
		
		txtPrescricao = new JTextArea();
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
				JButton btnSalvar = new JButton("Salvar");
				btnSalvar.setBackground(Color.WHITE);
				btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnSalvar.setForeground(new Color(34, 139, 34));
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							/*
							 * CARREGAR DADOS
							 */
							Vacina vacina= new Vacina();
							vacina.setNome(txtNome.getText());
							vacina.setPrescricao(txtPrescricao.getText());
							
							if (!validaCampos()) {
								JOptionPane.showMessageDialog(null, "Preencha todos os campos", "Validação de campos", 
										JOptionPane.ERROR_MESSAGE);
								return;
							} else {
								
								VacinaControl.getInstance().salvar(vacina);
								limpar();
								JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!", "Confirmação de cadastro/atualização", 
										JOptionPane.INFORMATION_MESSAGE);
								
								carregarTable();
							}
						} catch (Throwable ex) {
							ex.printStackTrace();
							Utils.msgExcption(ex.getMessage());	
						}						
					}

				});
				
				JButton btnNovo = new JButton("Novo");
				btnNovo.setBackground(Color.WHITE);
				btnNovo.setForeground(new Color(0, 0, 255));				
				btnNovo.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						txtId.setText("");
						txtNome.setText("");
						txtPrescricao.setText("");
					}
				});
				btnNovo.setActionCommand("OK");
				buttonPane.add(btnNovo);
				
				JButton btnApagar = new JButton("Apagar");
				btnApagar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						
					}
				});
				btnApagar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnApagar.setForeground(Color.RED);
				btnApagar.setBackground(Color.WHITE);
				btnApagar.setActionCommand("OK");
				buttonPane.add(btnApagar);
				btnSalvar.setActionCommand("OK");
				buttonPane.add(btnSalvar);
				getRootPane().setDefaultButton(btnSalvar);
			}
			{
				JButton btnFechar = new JButton("Fechar");
				btnFechar.setFont(new Font("Tahoma", Font.BOLD, 11));
				btnFechar.setForeground(Color.BLACK);
				btnFechar.setBackground(Color.WHITE);
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				btnFechar.setActionCommand("Cancel");
				buttonPane.add(btnFechar);
			}
		}
		
		carregarTable();
		
	}
	
	private void limpar() {
		txtId.setText("");
		txtNome.setText("");
		txtPrescricao.setText("");
	}
	
	private void carregarDados(Vacina entity) {
		txtId.setText(entity.getId()+"");
		txtNome.setText(entity.getNome());
		txtPrescricao.setText(entity.getPrescricao());
	}
	
	private boolean validaCampos(){
		boolean isValido= true;
		if ( txtNome.getText().length() == 0 || 
			txtPrescricao.getText().length() == 0
			) {
			isValido= false;
		}
		return isValido;
	}	
	
	private void carregarTable() {
		jTableModel.limpar();
		List<Vacina> lista= VacinaControl.getInstance().list(new Vacina());
		jTableModel.addList(lista);
		jTableModel.fireTableDataChanged();
	}
	
	private void formatarTabela(JTable jTable) {
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.getColumnModel().getColumn(0).setPreferredWidth(70);
		jTable.getColumnModel().getColumn(1).setPreferredWidth(230);
		jTable.getColumnModel().getColumn(2).setPreferredWidth(310);
	}		
}
