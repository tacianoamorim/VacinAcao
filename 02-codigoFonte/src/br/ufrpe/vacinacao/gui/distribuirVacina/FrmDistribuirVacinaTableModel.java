package br.ufrpe.vacinacao.gui.distribuirVacina;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.vacinacao.negocio.entidade.Estoque;

public class FrmDistribuirVacinaTableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Estoque> listaEstoque;
	private String[] colunas = new String[] { 
		"XXXX", "XXXXX", "XXXX", "XXXXX" 
	};

	/** Creates a new instance of TableModel */
	public FrmDistribuirVacinaTableModel(List<Estoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public FrmDistribuirVacinaTableModel() {
		this.listaEstoque = new ArrayList<Estoque>();
	}

	public int getRowCount() {
		return listaEstoque.size();
	}

	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Estoque aValue, int rowIndex) {
		Estoque representante = listaEstoque.get(rowIndex);

		representante.setId( aValue.getId() );
		representante.setLote(aValue.getLote());
		representante.setQuantidadeDoses(aValue.getQuantidadeDoses());
		representante.setUnidadeAtendimento(aValue.getUnidadeAtendimento());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Estoque representante = listaEstoque.get(rowIndex);

		switch (columnIndex) {
		case 0:
			representante.setId( Integer.parseInt(  aValue.toString() ) );
		case 1:
			representante.setLote(  null );
		case 2:
			representante.setQuantidadeDoses( Integer.parseInt( (String) aValue ) );
		case 3:
			representante.setUnidadeAtendimento( null );
			
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Estoque estoqueSelecionado = listaEstoque.get(rowIndex);
		String valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = estoqueSelecionado.getId()+"";
			break;
		case 1:
			valueObject = estoqueSelecionado.getLote().toString();
			break;
		case 2:
			valueObject = estoqueSelecionado.getQuantidadeDoses()+"";
			break;
		case 3:
			valueObject = estoqueSelecionado.getUnidadeAtendimento().toString();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Estoque.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Estoque getEstoque(int indiceLinha) {
		return listaEstoque.get(indiceLinha);
	}

	public void addEstoque(Estoque representante) {
		listaEstoque.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeEstoque(int indiceLinha) {
		listaEstoque.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeEstoques(List<Estoque> novosEstoques) {
		int tamanhoAntigo = getRowCount();
		listaEstoque.addAll(novosEstoques);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		listaEstoque.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return listaEstoque.isEmpty();
	}	
	
}
