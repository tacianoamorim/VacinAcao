package br.ufrpe.vacinacao.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.negocio.controlador.ServidorControl;
import br.ufrpe.vacinacao.negocio.controlador.UsuarioControl;
import br.ufrpe.vacinacao.negocio.entidade.Servidor;
import br.ufrpe.vacinacao.negocio.entidade.Usuario;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6234032704127284755L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtSenha;
	
	public static Servidor servidorLogado;
	public static Usuario usuarioLogado;
	
	private ButtonGroup buttonGroup;
	private JRadioButton rdbtnServidor, rdbtnUsuario;
	private JLabel lblCpf;
	private RadioButtonHandler handler;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FrmLogin dialog = new FrmLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setTitle("VacinAcao");
		setBounds(100, 100, 389, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Login");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(12, 10, 357, 24);
			contentPanel.add(lblNewLabel);
		}
		
		handler = new RadioButtonHandler();
		
		lblCpf = new JLabel("N\u00FAmero SUS:");
		lblCpf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCpf.setBounds(12, 111, 100, 15);
		contentPanel.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(12, 159, 100, 15);
		contentPanel.add(lblSenha);
		
		txtCodigo = new JTextField();
		txtCodigo.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCodigo.setBounds(122, 105, 217, 24);
		contentPanel.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSenha.setBounds(122, 154, 217, 24);
		contentPanel.add(txtSenha);
		txtSenha.setColumns(10);
		
		rdbtnServidor = new JRadioButton("Servidor");
		rdbtnServidor.setBackground(Color.WHITE);
		rdbtnServidor.setSelected(true);
		rdbtnServidor.setBounds(121, 58, 113, 23);
		contentPanel.add(rdbtnServidor);
		
		rdbtnUsuario = new JRadioButton("Usu\u00E1rio");
		rdbtnUsuario.setBackground(Color.WHITE);
		rdbtnUsuario.setBounds(250, 58, 89, 23);
		
		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnUsuario);
		buttonGroup.add(rdbtnServidor);
		
		rdbtnServidor.addItemListener(handler);
		rdbtnUsuario.addItemListener(handler);
		
		contentPanel.add(rdbtnUsuario);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setForeground(new Color(0, 128, 0));
				okButton.setBackground(new Color(255, 255, 255));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if ( rdbtnServidor.isSelected() ) {
							Servidor filtro= new Servidor();
							filtro.setMatricula( Integer.parseInt( txtCodigo.getText() ) );
							Servidor servidor= ServidorControl.getInstance().findByFilter(filtro);
							
							if ( txtSenha.getText() != null &&  txtCodigo.getText() != null &&
									servidor.getSenha().equalsIgnoreCase(txtSenha.getText()) ) {
								setVisible(false);
								
								usuarioLogado= null;
								servidorLogado= servidor;
								
								FrmPrincipal window= new FrmPrincipal();
								window.window.setVisible(true);
								
							} else {
								JOptionPane.showMessageDialog(null, "Servidor ou senha inválido.");
							}						
							
						} else {
							Usuario filtro= new Usuario();
							filtro.setNumeroSUS( txtCodigo.getText() );
							Usuario usuario= UsuarioControl.getInstance().findByFilter(filtro);
							
							if ( txtSenha.getText() != null &&  txtCodigo.getText() != null &&
									usuario.getSenha().equalsIgnoreCase(txtSenha.getText()) ) {
								setVisible(false);

								usuarioLogado= usuario;
								servidorLogado= null;
								
								FrmPrincipal window= new FrmPrincipal();
								window.window.setVisible(true);
								
							} else {
								JOptionPane.showMessageDialog(null, "Usuário ou senha inválido.");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setForeground(new Color(255, 0, 0));
				cancelButton.setBackground(new Color(255, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private class RadioButtonHandler implements ItemListener{
	  @Override
	  public void itemStateChanged(ItemEvent event) {
	   if(rdbtnUsuario.isSelected())
		   lblCpf.setText("N\u00FAmero SUS:");
	   else
		   lblCpf.setText("Matricula:"); 
	  }
	}
}
