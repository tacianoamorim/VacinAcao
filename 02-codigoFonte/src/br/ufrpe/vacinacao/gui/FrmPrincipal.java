package br.ufrpe.vacinacao.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

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
		window.setResizable(true);
		window.setBounds(100, 100, 828, 490);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("VacinAção");
		window.getContentPane().setLayout(new BoxLayout(window.getContentPane(), BoxLayout.X_AXIS));
		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.WHITE);
		window.setContentPane(desktop);

		//Make dragging a little faster but perhaps uglier.
	    desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
	    desktop.setLayout(null);
   
	    JMenuBar menuBar = new JMenuBar();
	    menuBar.setBackground(Color.WHITE);
	    menuBar.setBounds(0, 0, 254, 21);
	    menuBar.setSize(800, 35);
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
	     * MENU GERENCIA
	     */	    
	    JMenu mmGerenciamento = new JMenu("Gerenciamento");
	    menuBar.add(mmGerenciamento);
	    
	    JMenuItem mntGerenciamentoDistribuicaoVacina = new JMenuItem("Distribuição de vacinas");
	    mntGerenciamentoDistribuicaoVacina.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
//	    		FrmTipoIIDocumento window=new FrmTipoIIDocumento();
//	    		window.setVisible(true);
	    	}
	    });
	    mmGerenciamento.add(mntGerenciamentoDistribuicaoVacina);
	    
	    
	    /**
	     * MENU RELATORIO
	     */	   	    
	    JMenu mnRelatorio = new JMenu("Relatório");
	    menuBar.add(mnRelatorio);
	    
	    JMenuItem mntRelatorioEstoque = new JMenuItem("Estoque");
	    mntRelatorioEstoque.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {

	    	}
	    });
	    mnRelatorio.add(mntRelatorioEstoque);
	    
//	    JMenuItem mntmDocumento = new JMenuItem("Documento");
//	    mnRelatorio.add(mntmDocumento);
//	    
//	    JMenuItem mntmJuizado = new JMenuItem("Juizado");
//	    mnRelatorio.add(mntmJuizado);
//	    
//	    JMenu mnRelatorio = new JMenu("Relatorio");
//	    menuBar.add(mnRelatorio);
//	    
//	    JMenuItem mntmRelatrioResumido = new JMenuItem("Relatório Resumido Processo");
//	    mntmRelatrioResumido.addActionListener(new ActionListener() {
//	    	public void actionPerformed(ActionEvent arg0) {
//	    		FrmRelatorioResumido window= new FrmRelatorioResumido();
//	    		window.setVisible(true);
//	    	}
//	    });
//	    mnRelatorio.add(mntmRelatrioResumido);
//	    
//	    JMenuItem mntmRelatrioDetalhadoComunicacao = new JMenuItem("Relatório Detalhado Comunicacao");
//	    mnRelatorio.add(mntmRelatrioDetalhadoComunicacao);
//	    
//	    JMenuItem mntmRelatrioDetalhadoConciliacao = new JMenuItem("Relatório Detalhado Conciliacao");
//	    mnRelatorio.add(mntmRelatrioDetalhadoConciliacao);
	    
	    JPanel pnlCorpo = new JPanel();
	    pnlCorpo.setBounds(0, 51, 822, 410);
	    pnlCorpo.setBackground(new Color(255, 255, 255));
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
