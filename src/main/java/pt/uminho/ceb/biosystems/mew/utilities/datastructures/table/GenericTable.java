package pt.uminho.ceb.biosystems.mew.utilities.datastructures.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pt.uminho.ceb.biosystems.mew.utilities.io.Delimiter;

public class GenericTable {
	
	public static final Delimiter DEFAULT_COL_SEP 	= Delimiter.TAB;
	public static final String DEFAULT_LINE_SEP 	= "\n";
	
	protected Delimiter colSep;
	protected String lineSep;
	protected boolean hasHeaders;
	protected Map<String, Integer> headers;
	protected List<TableRow> rows;
	private ICellFactory cellFactory;
	
	public GenericTable(boolean hasHeaders) {
		this(hasHeaders, DEFAULT_COL_SEP, DEFAULT_LINE_SEP);
	}
	
	public GenericTable() {
		this(false);
	}
	
	public GenericTable(boolean hasHeaders, Delimiter colSep, String lineSep) {
		this.hasHeaders = hasHeaders;
		this.rows = new ArrayList<TableRow>();
		this.colSep = colSep;
		this.lineSep = lineSep;
		this.cellFactory = new DefaultCellFactory();
	}
	
	public void setHeaders(Map<String, Integer> headers) {
		this.headers = headers;
	}
	
	public void addRow(TableRow row) {
		if(hasHeaders)
			row.setHeaders(headers);
		
		rows.add(row);
	}
	
	public void addRow(int index, TableRow row) {
		if(hasHeaders)
			row.setHeaders(headers);
		
		rows.add(index, row);
	}
	
	public void setRow(int index, TableRow row) {
		if(hasHeaders)
			row.setHeaders(headers);
		
		rows.set(index, row);
	}
	
	public TableRow getRow(int index) {
		return rows.get(index);
	}
	
	public TableCell getCell(int index, String col) throws RuntimeException{
		if(!hasHeaders)
			throw new RuntimeException("Table has no headers");
		else
			return rows.get(index).get(col);
	}
	
	public TableCell getCell(int index, int col) {
		return rows.get(index).get(col);
	}
	
	public String getColSep() {
		return colSep.toString();
	}

	public void parseHeader(String headersLine) {
		this.headers = new HashMap<String, Integer>();
		String[] headersValues = headersLine.split(getColSep());
		for(int i = 0; i < headersValues.length; i++)
			this.headers.put(headersValues[i], i);
	}

	public void parseLine(String line) {
		TableRow row = new TableRow();
		String[] rawCells = line.split(getColSep());
		int i = 0;
		for(String cell : rawCells)
			row.addCell(i, cellFactory.build(cell, ++i));
		addRow(row);
	}
	
	
}
