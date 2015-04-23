package pt.uminho.ceb.biosystems.mew.utilities.datastructures.table;

import pt.uminho.ceb.biosystems.mew.utilities.datastructures.PrimitiveTypes;

public class TableCell {

	protected Object value;
	protected PrimitiveTypes type;
	public TableCell(Object value, PrimitiveTypes type) {
		this.value = value;
		this.type = type;
	}
	
}
