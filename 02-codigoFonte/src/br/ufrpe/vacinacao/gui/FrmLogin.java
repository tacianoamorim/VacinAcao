package br.ufrpe.vacinacao.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufrpe.vacinacao.negocio.controlador.UsuarioControl;
import br.ufrpe.vacinacao.negocio.entidade.Usuario;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6234032704127284755L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigoSUS;
	private JTextField txtSenha;
	public static boolean usuarioLogado= false;

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
		setTitle("VacinAção");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Login");
			lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(12, 10, 418, 24);
			contentPanel.add(lblNewLabel);
		}
		
		JLabel lblCpf = new JLabel("N SUS:");
		lblCpf.setBounds(58, 84, 66, 15);
		contentPanel.add(lblCpf);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(40, 126, 66, 15);
		contentPanel.add(lblSenha);
		
		txtCodigoSUS = new JTextField();
		txtCodigoSUS.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtCodigoSUS.setBounds(122, 77, 222, 24);
		contentPanel.add(txtCodigoSUS);
		txtCodigoSUS.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtSenha.setBounds(120, 119, 224, 24);
		contentPanel.add(txtSenha);
		txtSenha.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						Usuario filtro= new Usuario();
						filtro.setNumeroSUS( txtCodigoSUS.getText() );
						Usuario usuario= UsuarioControl.getInstance().findByFilter(filtro);
						
						if ( usuario.getSenha().equalsIgnoreCase(txtSenha.getText()) ) {
							setVisible(false);
							usuarioLogado= true;
							FrmPrincipal window= new FrmPrincipal();
							window.window.setVisible(true);
							
							UsuarioControl.getInstance().inserir();
							
						} else {
							JOptionPane.showMessageDialog(null, "Usuário ou senha inválido.");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
