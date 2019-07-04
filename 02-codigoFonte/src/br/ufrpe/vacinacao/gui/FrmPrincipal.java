package br.ufrpe.vacinacao.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import br.ufrpe.vacinacao.gui.vacina.FrmVacina;

public class FrmPrincipal {

	JFrame window;
	private JDesktopPane desktop;
	
	/**
	 * Create the application.
	 */
	public FrmPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		window = new JFrame();
		window.setResizable(false);
		window.setBounds(100, 100, 900, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("VacinA\u00E7\u00E3o");
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		Color background= new Color(60, 179, 113);
		if ( FrmLogin.usuarioLogado != null ) {
			background= Color.WHITE;
		}
		
		desktop = new JDesktopPane();
		desktop.setBackground(background);
		window.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(null);
   
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setBackground(Color.WHITE);
	    menuBar.setBounds(0, 0, 254, 21);
	    menuBar.setSize(900, 38);
	    desktop.add(menuBar);
	    
	    /**
	     * MENU CADASTRO
	     */
	    JMenu mmCadastro = new JMenu("Cadastro");
	    menuBar.add(mmCadastro);
	    
	    // --> SUBMENU
	    JMenuItem mntCadastroVacina = new JMenuItem("Vacina");
	    mntCadastroVacina.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		FrmVacina window= new FrmVacina();
	    		window.setVisible(true);
	    	}
	    });
	    mmCadastro.add(mntCadastroVacina);
	    
	    JMenuItem mntCadastroLoteVacina = new JMenuItem("Lote de vacina");
	    mntCadastroLoteVacina.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	}
	    });
	    mmCadastro.add(mntCadastroLoteVacina);

	    /**
	     * MENU GERENCIA FEDERAL
	     */	    
	    JMenu mmGerenciamentoFederal = new JMenu("Ger\u00EAncia Federal");
	    menuBar.add(mmGerenciamentoFederal);
	    
	    /**
	     * MENU GERENCIA ESTADUAL
	     */	    
	    JMenu mmGerenciamentoEstadual = new JMenu("Ger\u00EAncia Estadual");
	    menuBar.add(mmGerenciamentoEstadual);
	    
	    JMenuItem mntGerenciamentoDistribuicaoVacina = new JMenuItem("Distribui\u00E7\u00E3o Estadual de vacinas");
	    mntGerenciamentoDistribuicaoVacina.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		FrmTipoIIDocumento window=new FrmTipoIIDocumento();
//	    		window.setVisible(true);
	    	}
	    });
	    mmGerenciamentoEstadual.add(mntGerenciamentoDistribuicaoVacina);
	    
	    
	    /**
	     * MENU RELATORIO
	     */	   	    
	    JMenu mmRelatorio = new JMenu("Relat\u00F3rio");
	    menuBar.add(mmRelatorio);
	    
	    JMenuItem mntRelatorioEstoque = new JMenuItem("Estoque Estadual");
	    mntRelatorioEstoque.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	}
	    });
	    mmRelatorio.add(mntRelatorioEstoque);
	    
	    /**
	     * MENU SAIR
	     */	  
	    JButton mnFechar = new JButton("");
	    mnFechar.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		FrmLogin windowLogin= new FrmLogin();
	    		windowLogin.setVisible(true);	    		
	    		window.dispose();
	    	}
	    });
	    mnFechar.setBackground(Color.WHITE);
//	    mnFechar.setIcon(new ImageIcon(FrmPrincipal.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error.png")));
	    mnFechar.setBounds(356, 11, 48, 48);
	    menuBar.add(mnFechar);
	    
	    // DESABILITA AS OPCOES GERENCIAIS
	    if ( FrmLogin.usuarioLogado != null ) {
	    	mmCadastro.setVisible(false);
	    	mmRelatorio.setVisible(false);
	    	mmGerenciamentoEstadual.setVisible(false);
	    	mmGerenciamentoFederal.setVisible(false);
	    }
	    
	    JPanel pnlCorpo = new JPanel();
	    pnlCorpo.setBounds(0, 51, 822, 410);
	    pnlCorpo.setBackground(background);
	    desktop.add(pnlCorpo);
	    pnlCorpo.setLayout(null);
	    
	}
	
	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
	}
}
