package br.ufrpe.vacinacao.gui.lote;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.ufrpe.vacinacao.negocio.entidade.Laboratorio;
import br.ufrpe.vacinacao.negocio.entidade.Lote;
import br.ufrpe.vacinacao.negocio.entidade.Vacina;
import br.ufrpe.vacinacao.util.Utils;

public class FrmLoteTableModel extends AbstractTableModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7419763598897891865L;
	
	private List<Lote> list;
	private String[] colunas = new String[] { 
		"C\u00F3digo", "Laboratório", "Vacina", "Qtde doses"
	};

	/** Creates a new instance of TableModel */
	public FrmLoteTableModel(List<Lote> listaLote) {
		this.list = listaLote;
	}

	public FrmLoteTableModel() {
		this.list = new ArrayList<Lote>();
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

	public void setValueAt(Lote aValue, int rowIndex) {
		Lote lote = list.get(rowIndex);

		
		lote.setId( aValue.getId() );
		// laboratorio
		// Vacina
		lote.setQuantidadeDose(aValue.getQuantidadeDose());
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
		fireTableCellUpdated(rowIndex, 3);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Lote lote = list.get(rowIndex);
		switch (columnIndex) {
		case 0:
			lote.setId( Integer.parseInt( aValue.toString() ) );
		case 1:
			lote.setNumero( aValue.toString() );
		case 2:
			Laboratorio laboratorio= new Laboratorio();
			int id= Integer.parseInt(Utils.getId(aValue.toString(), "-"));
			laboratorio.setId(id);
			lote.setLaboratorio(laboratorio);
			
		case 3:
			Vacina vacina= new Vacina();
			int idVac= Integer.parseInt(Utils.getId(aValue.toString(), "-"));
			vacina.setId(idVac);
			lote.setVacina(vacina);
			
		default:
			System.err.println("indice da coluna invalido");
		}
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Lote loteSelecionado = list.get(rowIndex);
		Object valueObject = null;
		switch (columnIndex) {
		case 0:
			valueObject = loteSelecionado.getId()+"";
			break;
		case 1:
			valueObject = loteSelecionado.getLaboratorio().getNome();
			break;
		case 2:
			valueObject = loteSelecionado.getVacina().getNome();
			break;
		case 3:
			valueObject = loteSelecionado.getQuantidadeDose();
			break;			
		default:
			System.err.println("indice invalido para propriedade do bean Lote.class");
		}
		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Lote get(int indiceLinha) {
		return list.get(indiceLinha);
	}

	public void add(Lote representante) {
		list.add(representante);
		int ultimoIndice = getRowCount() - 1;
		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void remove(int indiceLinha) {
		list.remove(indiceLinha);
		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addList(List<Lote> novosLotes) {
		int tamanhoAntigo = getRowCount();
		list.addAll(novosLotes);
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
