package br.ufrpe.spjc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrpe.spjc.util.Constantes;

public class FrmLogin extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1864210157235754121L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCodigo;
	private JTextField txtSenha;
	
	private JRadioButton jrbProfessor, jrbAluno, jrbAdministrador;
	private RadioButtonHandler handler;
	private ButtonGroup grupo;
	
	private JButton btnCadastro, tbnLogar;
	

	/**
	 * Create the dialog.
	 */
	public FrmLogin() {
		setTitle("Login");
		setType(Type.POPUP);
		setBounds(100, 100, 398, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		//cargaInicial();
		
		{
			JLabel lblNomeUsuario = new JLabel("Nome usuario:");
			lblNomeUsuario.setBounds(10, 19, 102, 14);
			contentPanel.add(lblNomeUsuario);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setText("paulo");
			txtCodigo.setBounds(108, 11, 131, 30);
			txtCodigo.setColumns(10);
			contentPanel.add(txtCodigo);
		}
		{
			txtSenha = new JTextField();
			txtSenha.setText("123");
			txtSenha.setBounds(108, 50, 131, 30);
			txtSenha.setColumns(10);
			contentPanel.add(txtSenha);
		}
		{
			jrbProfessor = new JRadioButton("Professor");
			jrbProfessor.setBounds(259, 7, 117, 23);
			jrbProfessor.setSelected(true);
			jrbProfessor.setBackground(Color.WHITE);
			contentPanel.add(jrbProfessor);
		}
		{
			jrbAluno = new JRadioButton("Aluno");
			jrbAluno.setBounds(259, 33, 117, 23);
			jrbAluno.setBackground(Color.WHITE);
			contentPanel.add(jrbAluno);
		}
		{
			JLabel label = new JLabel("Senha:");
			label.setBounds(10, 58, 88, 14);
			contentPanel.add(label);
		}
		{
			jrbAdministrador = new JRadioButton("Administrador");
			jrbAdministrador.setBounds(259, 57, 117, 23);
			jrbAdministrador.setBackground(Color.WHITE);
			contentPanel.add(jrbAdministrador);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCadastro = new JButton("");
				btnCadastro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						FrmPrincipal.perfilLogado= null;
						
						// Cadastrar professor
						if ( jrbProfessor.isSelected() ) {
							FrmPrincipal.acaoInicializacao= Constantes.LOGIN_NOVO_CADASTRO_PROFESSOR;
							
						}
						
						// Cadastrar aluno
						if ( jrbAluno.isSelected() ) {
							FrmPrincipal.acaoInicializacao= Constantes.LOGIN_NOVO_CADASTRO_ALUNO;
							//FrmAluno window = new FrmAluno();
							//window.setVisible(true);
						}
						
					}
				});
				btnCadastro.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/novo_32.png")));
				buttonPane.add(btnCadastro);
				getRootPane().setDefaultButton(btnCadastro);
			}
			{
				tbnLogar = new JButton("");
				tbnLogar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							// Limpa a variavel
							FrmPrincipal.acaoInicializacao= null;
							
							if ( txtCodigo.getText() != null && 
									txtCodigo.getText().trim().length() == 0) {
								JOptionPane.showMessageDialog(null,"Informe o usuario de login");
								
							} else if ( txtSenha.getText() != null && 
									txtSenha.getText().trim().length() == 0 ) {
								JOptionPane.showMessageDialog(null,"Informe a senha");
								
							} else {
								
								String tipo= "";
								if(jrbAluno.isSelected())
									tipo= Constantes.PERFIL_ALUNO;
								if(jrbProfessor.isSelected())
									tipo= Constantes.PERFIL_PROFESSOR;
								if(jrbAdministrador.isSelected())
									tipo= Constantes.PERFIL_ADM;
								
								//boolean loginLiberado= 	Fachada.getInstance().login( txtCodigo.getText(), 
								//		txtSenha.getText(), tipo );
								
//								if ( loginLiberado ) {
//									setVisible(false);
//									FrmPrincipal.perfilLogado= tipo;
//									FrmPrincipal window = new FrmPrincipal();
//									window.frame.setVisible(true);
//								};
							}
							
						} catch (Exception ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null,"Verifique o usuario e a senha informada.");
						}
						
						
					}
				});
				tbnLogar.setIcon(new ImageIcon(FrmLogin.class.getResource("/image/login_32.png")));
				buttonPane.add(tbnLogar);
			}
		}
		
		grupo = new ButtonGroup();
		grupo.add(jrbAdministrador);
		grupo.add(jrbAluno);
		grupo.add(jrbProfessor);
		
		handler = new RadioButtonHandler();
		jrbAdministrador.addItemListener(handler);
		jrbAluno.addItemListener(handler);
		jrbProfessor.addItemListener(handler);
	}
	
	private class RadioButtonHandler implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			if(jrbAdministrador.isSelected()) {
				btnCadastro.setEnabled(false);
				
			} else {
				btnCadastro.setEnabled(true);
				//if(jrbAluno.isSelected())
					//JOptionPane.showMessageDialog(null,"Aluno!");
				//if(jrbProfessor.isSelected())
					//JOptionPane.showMessageDialog(null,"Prof!");				
			}
		}
		
	}
	


}
