package pt.uminho.ceb.biosystems.mew.utilities.datastructures.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableRow{
	
	protected Map<String, Integer> headers;
	protected List<TableCell> cells;

	public TableRow() {
		this(null);
	}
	
	public TableRow(Map<String, Integer> headers) {
		this.headers = headers;
		this.cells = new ArrayList<TableCell>();
	}
	
	public void setHeaders(Map<String, Integer> headers) {
		this.headers = headers;
	}

	public TableCell get(String columnName) {
		if(headers != null) 
			throw new RuntimeException("Row has no headers");
		else {
			int index = headers.get(columnName);
			return get(index);
		}
	}

	public TableCell get(int index) {
		return cells.get(index);
	}
	
	public void addCell(int index, TableCell cell) {
		cells.add(cell);
	}

	
	
}
