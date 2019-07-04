package br.ufrpe.vacinacao.gui.distribuirVacina;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.vacinacao.negocio.entidade.Estoque;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.UnidadeAtendimento;

public class FrmDistribuirVacinaTableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Estoque> list;
	private String[] colunas = new String[] { 
		"C\u00F3digo", "Unid. atendimento", "Vacina", "Lote", "Qtde de doses"
	};

	/** Creates a new instance of TableModel */
	public FrmDistribuirVacinaTableModel(List<Estoque> listaEstoque) {
		this.list = listaEstoque;
	}

	public FrmDistribuirVacinaTableModel() {
		this.list = new ArrayList<Estoque>();
	}

	public int getRowCount() {
		return list.size();
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
		Estoque estoque = list.get(rowIndex);

		estoque.setId( aValue.getId() );
		estoque.setUnidadeAtendimento(aValue.getUnidadeAtendimento());
		estoque.setLote(aValue.getLote());
		estoque.setQuantidadeDoses(aValue.getQuantidadeDoses());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
		fireTableCellUpdated(rowIndex, 4);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Estoque estoque = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			estoque.setId( Integer.parseInt( aValue.toString() ) );
		case 1:
			estoque.setUnidadeAtendimento( (UnidadeAtendimento) aValue );
		case 2:
			estoque.setLote( (Lote) aValue );
		case 3:
			estoque.setLote( (Lote) aValue );			
		case 4:
			estoque.setQuantidadeDoses( Integer.parseInt( (String) aValue ) );
			
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Estoque estoqueSelecionado = list.get(rowIndex);
		Object valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = estoqueSelecionado.getId()+"";
			break;
		case 1:
			valueObject = estoqueSelecionado.getUnidadeAtendimento();
			break;
		case 2:
			valueObject = estoqueSelecionado.getLote();
			break;
		case 3:
			valueObject = estoqueSelecionado.getLote();
			break;
		case 4:
			valueObject = estoqueSelecionado.getQuantidadeDoses()+"";
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

	public Estoque get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public void add(Estoque representante) {
		list.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void remove(int indiceLinha) {
		list.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void add(List<Estoque> novosEstoques) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosEstoques);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		list.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}	
	
}
