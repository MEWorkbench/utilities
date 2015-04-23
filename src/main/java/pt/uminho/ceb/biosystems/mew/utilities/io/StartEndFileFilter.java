package pt.uminho.ceb.biosystems.mew.utilities.io;

import java.io.File;
import java.io.FileFilter;

public class StartEndFileFilter implements FileFilter {
	protected String	extension;
	protected String	prefix;
	
	public StartEndFileFilter(String start, String end) {
		extension = end;
		prefix = start;
	}
	
	public boolean accept(File arg0) {
		boolean ret = false;
		if (arg0.isFile()) {
			if (arg0.getName().startsWith(prefix) && arg0.getName().endsWith(extension)) {
				return true;
			}
		}
		return ret;
	}
	
}