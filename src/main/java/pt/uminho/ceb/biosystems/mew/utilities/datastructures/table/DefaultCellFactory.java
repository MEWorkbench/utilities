package pt.uminho.ceb.biosystems.mew.utilities.datastructures.table;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.PrimitiveTypes;

public class DefaultCellFactory implements ICellFactory{

	public TableCell build(String cell, int i) {
		TableCell tableCell = null;
		for(PrimitiveTypes type : PrimitiveTypes.values()) {
			if (type.isConvertable(cell)) {
				tableCell = new TableCell(type.getObjectType(cell), type);
				break;
			}
		}
		if(tableCell == null) tableCell = new TableCell(cell, PrimitiveTypes.STRING);
		return tableCell;
	}

}
