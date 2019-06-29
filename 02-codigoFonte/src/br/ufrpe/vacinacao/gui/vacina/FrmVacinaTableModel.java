package br.ufrpe.vacinacao.gui.vacina;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.vacinacao.negocio.entidade.Vacina;

public class FrmVacinaTableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Vacina> list;
	private String[] colunas = new String[] { 
		"C\u00F3digo", "Nome", "Prescrição"
	};

	/** Creates a new instance of TableModel */
	public FrmVacinaTableModel(List<Vacina> listaVacina) {
		this.list = listaVacina;
	}

	public FrmVacinaTableModel() {
		this.list = new ArrayList<Vacina>();
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

	public void setValueAt(Vacina aValue, int rowIndex) {
		Vacina vacina = list.get(rowIndex);

		vacina.setId( aValue.getId() );
		vacina.setNome(aValue.getNome());
		vacina.setPrescricao(aValue.getPrescricao());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Vacina vacina = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			vacina.setId( Integer.parseInt( aValue.toString() ) );
		case 1:
			vacina.setNome( aValue.toString() );
		case 2:
			vacina.setPrescricao( aValue.toString() );
			
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Vacina vacinaSelecionado = list.get(rowIndex);
		Object valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = vacinaSelecionado.getId()+"";
			break;
		case 1:
			valueObject = vacinaSelecionado.getNome();
			break;
		case 2:
			valueObject = vacinaSelecionado.getPrescricao();
			break;
		default:
			System.err.println("indice invalido para propriedade do bean Vacina.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Vacina get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public void add(Vacina representante) {
		list.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void remove(int indiceLinha) {
		list.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addList(List<Vacina> novosVacinas) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosVacinas);
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
